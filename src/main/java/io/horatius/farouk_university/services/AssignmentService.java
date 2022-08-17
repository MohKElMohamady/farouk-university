package io.horatius.farouk_university.services;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import io.horatius.farouk_university.dao.AssessmentBySubmission;
import io.horatius.farouk_university.dao.AssignmentByCourse;
import io.horatius.farouk_university.dao.SubmissionByAssignment;
import io.horatius.farouk_university.dao.User;
import io.horatius.farouk_university.models.Assessment;
import io.horatius.farouk_university.models.Assignment;
import io.horatius.farouk_university.models.Submission;
import io.horatius.farouk_university.repositories.AssessmentRepository;
import io.horatius.farouk_university.repositories.AssignmentRepository;
import io.horatius.farouk_university.repositories.SubmissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.UUID;

@Service
@Slf4j
public class AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private AssessmentRepository assessmentRepository;
    @Autowired
    private SubmissionRepository submissionRepository;

    public Mono<Assignment> createAssignment(Assignment a, Mono<Principal> principal){
        Mono<User> principalPlaceholder = Mono.just(new User("dibiasky", "whatifitsnotequal", "Kate", "Dibiasky"));

        /*
         * TODO: Figure out a way to throw an exception when the user who attempts to create the assignment is not the
         *  course creator.
         */
        return principalPlaceholder.<Assignment>flatMap(p -> {
                        return this.assignmentRepository.save(new AssignmentByCourse(Uuids.timeBased(), a.getCourse().getCourseId(), a.getDescription(),  p.getUsername()))
                                .<Assignment>flatMap(savedAssignment -> {
                                    return Mono.<Assignment>just(new Assignment.Builder()
                                            .course(savedAssignment.getCourseId())
                                            .assignmentId(savedAssignment.getAssignmentId())
                                            .description(savedAssignment.getDescription())
                                            .build());
                                });
                    });
    }

    public Flux<Assignment> getAssignmentsOfCourse(UUID courseId) {
        return assignmentRepository.findAllByCourseId(courseId)
                .flatMap(a -> {
                    return Flux.just(new Assignment.Builder()
                                                   .course(courseId)
                                                   .assignmentId(a.getAssignmentId())
                                                   .description(a.getDescription())
                                                   .build());
                });
    }
    public Flux<Assessment> getUsersAnswerAndGradesOfAssignment(UUID courseId){
        Mono<User> principalPlaceholder = Mono.just(new User("dibiasky", "whatifitsnotequal", "Kate", "Dibiasky"));
        log.info("First step is to fetch the assignments of the course " + courseId);
        return principalPlaceholder.flatMapMany(user -> {
            return assignmentRepository.findAllByCourseId(courseId).flatMap(assignment -> {
                        log.info("Found the assignment of id" + assignment.getAssignmentId() + " that will be used to fetched the submission");
                return Flux.zip(
                        Flux.just(assignment),
                        submissionRepository.findFirstByAssignmentId(assignment.getAssignmentId())
                                            .switchIfEmpty(Flux.defer(() -> {
                                                log.info("No submission found for assignment of id " + assignment.getAssignmentId() + ", hence an assignment without an answer will be provided");
                                                var notYetSubmitted = new SubmissionByAssignment();
                                                notYetSubmitted.setAnswer("Not Yet Submitted");
                                                return Flux.just(notYetSubmitted);
                                            })));
            }).flatMap((assignmentAndSubmissionTuple) -> {
                var assignment = assignmentAndSubmissionTuple.getT1();
                var submission = assignmentAndSubmissionTuple.getT2();
                        /*
                         * If the submission provided by the previous pipe was with the answer "Not Yet Submitted", then
                         * makes sense to previous an assessment that was not graded (.i.e. with -1)
                         */
                        if(submission.getAnswer().equals("Not Yet Submitted")){
                            var notYetGradedAssessment = new AssessmentBySubmission();
                            notYetGradedAssessment.setGrade(-1);
                            return Flux.zip(Flux.<AssignmentByCourse>just(assignment),
                                    Flux.<SubmissionByAssignment>just(submission),
                                    Flux.<AssessmentBySubmission>just(notYetGradedAssessment));
                        }
                /*
                 * This step however will be reached, when there will be a submission, but NOT yet graded
                 *
                 */
                return Flux.zip(Flux.<AssignmentByCourse>just(assignment),
                                Flux.<SubmissionByAssignment>just(submission),
                                assessmentRepository.findAssessmentBySubmissionId(submission.getSubmissionId())
                                                    .filter(assessment -> assessment.getUsername().equals(user.getUsername()))
                                                    /*
                                                     * If there was no assessment found for that assignment for that user,
                                                     * an assessment will be return with -1 grading (subject to change later
                                                     * after the enum is used) and with comment not yet graded
                                                     */
                                                    .switchIfEmpty(Flux.defer(() -> {
                                                        var assessment = new AssessmentBySubmission();
                                                        assessment.setGrade(-1);
                                                        assessment.setComment("NOT YET GRADED");
                                                        return Flux.just(assessment);
                                                    })));
                /*
                 * Final step, transforming the data transfer object to model
                 */
            }).flatMap((assignmentSubmissionAssessmentTuple) -> {
                AssignmentByCourse assignmentByCourse = assignmentSubmissionAssessmentTuple.getT1();
                SubmissionByAssignment submissionByAssignment = assignmentSubmissionAssessmentTuple.getT2();
                AssessmentBySubmission assessmentBySubmission = assignmentSubmissionAssessmentTuple.getT3();
                Submission submission = new Submission.Builder().submissionId(submissionByAssignment.getSubmissionId())
                                                                .answer(submissionByAssignment.getAnswer())
                                                                .assignment(assignmentByCourse.getAssignmentId())
                                                                .build();
                Assessment assessment = new Assessment.Builder()
                                                      .assignmentId(assessmentBySubmission.getAssessmentId())
                                                      .grade(assessmentBySubmission.getGrade())
                                                      .comment(assessmentBySubmission.getComment())
                                                      .submission(submission)
                                                      .build();
                log.info("Finally, the delivered assessment is" + assessment);
                return Flux.just(assessment);
            });
        });
    }
}
