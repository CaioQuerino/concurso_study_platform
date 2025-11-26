import { Routes } from '@angular/router';
import { ListarQuestoesComponent } from './pages/listar-questoes/listar-questoes.component';
import { CriarQuestaoComponent } from './pages/criar-questao/criar-questao.component';
import { EditarQuestaoComponent } from './pages/editar-questao/editar-questao.component';


export const routes: Routes = [
    {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full',
    },

  {
    path: '',
    loadComponent: () => import('./pages/listar-questoes/listar-questoes.component').then( m => m.ListarQuestoesComponent)
  },

  {
    path: 'editar/:id',
    loadComponent: () => import('./pages/editar-questao/editar-questao.component').then( m => m.EditarQuestaoComponent)
  },

  {
    path: 'criar',
    loadComponent: () => import('./pages/criar-questao/criar-questao.component').then( m => m.CriarQuestaoComponent)
  },
];