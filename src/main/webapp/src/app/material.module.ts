import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatGridListModule} from '@angular/material/grid-list'

const materialComponents = [
  MatGridListModule
]


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    materialComponents
  ],
  exports : [
    materialComponents
  ]
})
export class MaterialModule { }
