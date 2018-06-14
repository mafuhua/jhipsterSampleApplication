import { BaseEntity } from './../../shared';

export class Task2MySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public appId?: number,
        public name?: string,
        public desce?: string,
        public wxAppid?: string,
        public title?: string,
        public description?: string,
    ) {
    }
}
