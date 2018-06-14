/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { Task2MySuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/task-2-my-suffix/task-2-my-suffix-delete-dialog.component';
import { Task2MySuffixService } from '../../../../../../main/webapp/app/entities/task-2-my-suffix/task-2-my-suffix.service';

describe('Component Tests', () => {

    describe('Task2MySuffix Management Delete Component', () => {
        let comp: Task2MySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<Task2MySuffixDeleteDialogComponent>;
        let service: Task2MySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [Task2MySuffixDeleteDialogComponent],
                providers: [
                    Task2MySuffixService
                ]
            })
            .overrideTemplate(Task2MySuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(Task2MySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Task2MySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
