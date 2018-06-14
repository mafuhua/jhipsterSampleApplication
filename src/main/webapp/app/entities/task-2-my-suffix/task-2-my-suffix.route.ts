import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { Task2MySuffixComponent } from './task-2-my-suffix.component';
import { Task2MySuffixDetailComponent } from './task-2-my-suffix-detail.component';
import { Task2MySuffixPopupComponent } from './task-2-my-suffix-dialog.component';
import { Task2MySuffixDeletePopupComponent } from './task-2-my-suffix-delete-dialog.component';

export const task2Route: Routes = [
    {
        path: 'task-2-my-suffix',
        component: Task2MySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Task2S'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'task-2-my-suffix/:id',
        component: Task2MySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Task2S'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const task2PopupRoute: Routes = [
    {
        path: 'task-2-my-suffix-new',
        component: Task2MySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Task2S'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'task-2-my-suffix/:id/edit',
        component: Task2MySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Task2S'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'task-2-my-suffix/:id/delete',
        component: Task2MySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Task2S'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
