import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuditoriumContainerComponent } from './auditorium-container.component';

describe('AuditoriumContainerComponent', () => {
  let component: AuditoriumContainerComponent;
  let fixture: ComponentFixture<AuditoriumContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuditoriumContainerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuditoriumContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
