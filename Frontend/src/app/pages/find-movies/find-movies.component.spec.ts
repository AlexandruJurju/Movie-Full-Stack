import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindMoviesComponent } from './find-movies.component';

describe('FindMoviesComponent', () => {
  let component: FindMoviesComponent;
  let fixture: ComponentFixture<FindMoviesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FindMoviesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FindMoviesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
