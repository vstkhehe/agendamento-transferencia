<template>
    <main>
        <Navbar />

        <!-- table -->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="text-center">Buscar Agendamentos</h1>
                    
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Conta de Origem</th>
                                <th scope="col">Conta de Destino</th>
                                <th scope="col">Valor para Transferência</th>
                                <th scope="col">Valor da Taxa de Tranferência</th>
                                <th scope="col">Valor Total da Tranferência</th>
                                <th scope="col">Data para Transferência</th>
                                <th scope="col">Data do Agendamento</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">{{ agendamentos.id }}</th>
                                <td>{{ agendamentos.contaOrigem }}</td>
                                <td>{{ agendamentos.contaDestino }}</td>
                                <td>{{ agendamentos.valorTransferencia }}</td>
                                <td>{{ agendamentos.valorTransferenciaTaxa }}</td>
                                <td>{{ agendamentos.valorTransferenciaTotal }}</td>
                                <td>{{ agendamentos.dataTransferencia }}</td>
                                <td>{{ agendamentos.dataAgendamento }}</td>
                                <td>
                                    <a class="btn btn-primary" :href="`/edit/${agendamentos.id}`">Edit</a>
                                </td>
                                <td>    
                                    <button class="btn btn-danger mx-2" @click="deleteAgendamentos(agendamento.id)">Delete</button>
                                </td>
                            </tr>
                            <tr>
                                <th scope="col">Id</th>
                                <td>
                                    <input type="text" name="id" id="id" class="form-control" placeholder="0" required v-model="id">
                                </td>
                                <td>
                                    <button class="btn btn-primary mx-2" @click="findAgendamento(id)">Buscar</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </main>
</template>

<script>

import Navbar from '../components/Navbar.vue'

export default {
    name: 'FindAgendamento',
    components : {
        Navbar
    },
    data(){
        return {
            agendamentos: []
        }
    },

    methods: {
        getAgendamentos(){
            fetch(`http://localhost:8080/api/agendamento-transferencia/agendamentos`)
            .then(res => res.json())
            .then(data => {
                this.agendamentos = data
                console.log(data)
            })
        },
        deleteAgendamentos(id){
            fetch(`http://localhost:8080/api/agendamento-transferencia/${id}`, 
            {
                method: 'DELETE',
            })
            .then(data => {
                console.log(data)
            })
        },
        findAgendamento(id){
            fetch(`http://localhost:8080/api/agendamento-transferencia/${id}`, 
            {
                method: 'GET',
            })
            .then(res => res.json())
            .then(data => {
                this.agendamentos = data
                console.log(data)
            })
        }


    }
}

</script>