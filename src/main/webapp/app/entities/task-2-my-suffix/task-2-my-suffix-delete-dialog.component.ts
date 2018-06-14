import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Task2MySuffix } from './task-2-my-suffix.model';
import { Task2MySuffixPopupService } from './task-2-my-suffix-popup.service';
import { Task2MySuffixService } from './task-2-my-suffix.service';

@Component({
    selector: 'jhi-task-2-my-suffix-delete-dialog',
    templateUrl: './task-2-my-suffix-delete-dialog.component.html'
})
export class Task2MySuffixDeleteDialogComponent {

    task2: Task2MySuffix;

    constructor(
        private task2Service: Task2MySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.task2Service.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'task2ListModification',
                content: 'Deleted an task2'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-task-2-my-suffix-delete-popup',
    template: ''
})
export class Task2MySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private task2PopupService: Task2MySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.task2PopupService
                .open(Task2MySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
