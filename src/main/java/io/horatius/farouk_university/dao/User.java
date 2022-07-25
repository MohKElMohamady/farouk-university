package io.horatius.farouk_university.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "users")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class User {
    @PrimaryKey
    private String username;
    private String hashedPassword;
    private String firstName;
    private String lastName;

    public String getName(){
        return this.username;
    }
}
