import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Task2MySuffix } from './task-2-my-suffix.model';
import { Task2MySuffixPopupService } from './task-2-my-suffix-popup.service';
import { Task2MySuffixService } from './task-2-my-suffix.service';

@Component({
    selector: 'jhi-task-2-my-suffix-dialog',
    templateUrl: './task-2-my-suffix-dialog.component.html'
})
export class Task2MySuffixDialogComponent implements OnInit {

    task2: Task2MySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private task2Service: Task2MySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.task2.id !== undefined) {
            this.subscribeToSaveResponse(
                this.task2Service.update(this.task2));
        } else {
            this.subscribeToSaveResponse(
                this.task2Service.create(this.task2));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Task2MySuffix>>) {
        result.subscribe((res: HttpResponse<Task2MySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Task2MySuffix) {
        this.eventManager.broadcast({ name: 'task2ListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-task-2-my-suffix-popup',
    template: ''
})
export class Task2MySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private task2PopupService: Task2MySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.task2PopupService
                    .open(Task2MySuffixDialogComponent as Component, params['id']);
            } else {
                this.task2PopupService
                    .open(Task2MySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
