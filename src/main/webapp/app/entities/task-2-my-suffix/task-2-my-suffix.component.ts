import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Task2MySuffix } from './task-2-my-suffix.model';
import { Task2MySuffixService } from './task-2-my-suffix.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-task-2-my-suffix',
    templateUrl: './task-2-my-suffix.component.html'
})
export class Task2MySuffixComponent implements OnInit, OnDestroy {
task2S: Task2MySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private task2Service: Task2MySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search'] ?
            this.activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.task2Service.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: HttpResponse<Task2MySuffix[]>) => this.task2S = res.body,
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
       }
        this.task2Service.query().subscribe(
            (res: HttpResponse<Task2MySuffix[]>) => {
                this.task2S = res.body;
                this.currentSearch = '';
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.currentSearch = query;
        this.loadAll();
    }

    clear() {
        this.currentSearch = '';
        this.loadAll();
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInTask2S();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Task2MySuffix) {
        return item.id;
    }
    registerChangeInTask2S() {
        this.eventSubscriber = this.eventManager.subscribe('task2ListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
