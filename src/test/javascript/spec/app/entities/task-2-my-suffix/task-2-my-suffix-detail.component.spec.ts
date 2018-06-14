/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { Task2MySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/task-2-my-suffix/task-2-my-suffix-detail.component';
import { Task2MySuffixService } from '../../../../../../main/webapp/app/entities/task-2-my-suffix/task-2-my-suffix.service';
import { Task2MySuffix } from '../../../../../../main/webapp/app/entities/task-2-my-suffix/task-2-my-suffix.model';

describe('Component Tests', () => {

    describe('Task2MySuffix Management Detail Component', () => {
        let comp: Task2MySuffixDetailComponent;
        let fixture: ComponentFixture<Task2MySuffixDetailComponent>;
        let service: Task2MySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [Task2MySuffixDetailComponent],
                providers: [
                    Task2MySuffixService
                ]
            })
            .overrideTemplate(Task2MySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(Task2MySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Task2MySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Task2MySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.task2).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
