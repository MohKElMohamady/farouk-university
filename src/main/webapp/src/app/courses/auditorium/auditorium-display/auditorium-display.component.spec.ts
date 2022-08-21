import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuditoriumDisplayComponent } from './auditorium-display.component';

describe('AuditoriumDisplayComponent', () => {
  let component: AuditoriumDisplayComponent;
  let fixture: ComponentFixture<AuditoriumDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuditoriumDisplayComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuditoriumDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
