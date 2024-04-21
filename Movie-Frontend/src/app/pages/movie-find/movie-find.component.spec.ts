import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieFindComponent } from './movie-find.component';

describe('FindMoviesComponent', () => {
  let component: MovieFindComponent;
  let fixture: ComponentFixture<MovieFindComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MovieFindComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MovieFindComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
