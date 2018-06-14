/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { Task2MySuffixComponent } from '../../../../../../main/webapp/app/entities/task-2-my-suffix/task-2-my-suffix.component';
import { Task2MySuffixService } from '../../../../../../main/webapp/app/entities/task-2-my-suffix/task-2-my-suffix.service';
import { Task2MySuffix } from '../../../../../../main/webapp/app/entities/task-2-my-suffix/task-2-my-suffix.model';

describe('Component Tests', () => {

    describe('Task2MySuffix Management Component', () => {
        let comp: Task2MySuffixComponent;
        let fixture: ComponentFixture<Task2MySuffixComponent>;
        let service: Task2MySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [Task2MySuffixComponent],
                providers: [
                    Task2MySuffixService
                ]
            })
            .overrideTemplate(Task2MySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(Task2MySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Task2MySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Task2MySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.task2S[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
