import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    Task2MySuffixService,
    Task2MySuffixPopupService,
    Task2MySuffixComponent,
    Task2MySuffixDetailComponent,
    Task2MySuffixDialogComponent,
    Task2MySuffixPopupComponent,
    Task2MySuffixDeletePopupComponent,
    Task2MySuffixDeleteDialogComponent,
    task2Route,
    task2PopupRoute,
} from './';

const ENTITY_STATES = [
    ...task2Route,
    ...task2PopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        Task2MySuffixComponent,
        Task2MySuffixDetailComponent,
        Task2MySuffixDialogComponent,
        Task2MySuffixDeleteDialogComponent,
        Task2MySuffixPopupComponent,
        Task2MySuffixDeletePopupComponent,
    ],
    entryComponents: [
        Task2MySuffixComponent,
        Task2MySuffixDialogComponent,
        Task2MySuffixPopupComponent,
        Task2MySuffixDeleteDialogComponent,
        Task2MySuffixDeletePopupComponent,
    ],
    providers: [
        Task2MySuffixService,
        Task2MySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationTask2MySuffixModule {}
