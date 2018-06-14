import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Task2MySuffix } from './task-2-my-suffix.model';
import { Task2MySuffixService } from './task-2-my-suffix.service';

@Component({
    selector: 'jhi-task-2-my-suffix-detail',
    templateUrl: './task-2-my-suffix-detail.component.html'
})
export class Task2MySuffixDetailComponent implements OnInit, OnDestroy {

    task2: Task2MySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private task2Service: Task2MySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTask2S();
    }

    load(id) {
        this.task2Service.find(id)
            .subscribe((task2Response: HttpResponse<Task2MySuffix>) => {
                this.task2 = task2Response.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTask2S() {
        this.eventSubscriber = this.eventManager.subscribe(
            'task2ListModification',
            (response) => this.load(this.task2.id)
        );
    }
}
