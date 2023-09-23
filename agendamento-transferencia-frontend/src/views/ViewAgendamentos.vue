<template>
    <main>
        <Navbar />

        <!-- table -->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="text-center">Agendamentos</h1>
                    
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
                            <tr v-for="agendamento in agendamentos" :key="agendamento.id">
                                <th scope="row">{{ agendamento.id }}</th>
                                <td>{{ agendamento.contaOrigem }}</td>
                                <td>{{ agendamento.contaDestino }}</td>
                                <td>{{ agendamento.valorTransferencia }}</td>
                                <td>{{ agendamento.valorTransferenciaTaxa }}</td>
                                <td>{{ agendamento.valorTransferenciaTotal }}</td>
                                <td>{{ agendamento.dataTransferencia }}</td>
                                <td>{{ agendamento.dataAgendamento }}</td>
                                <td>
                                    <a class="btn btn-primary" :href="`/edit/${agendamento.id}`">Edit</a>
                                </td>
                                <td>    
                                    <button class="btn btn-danger mx-2" @click="deleteAgendamentos(agendamento.id)">Delete</button>
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
    name: 'ViewAgendamento',
    components : {
        Navbar
    },
    data(){
        return {
            agendamentos: []
        }
    },

    beforeMount(){
            this.getAgendamentos()
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
                this.getAgendamentos()
            })
        }
    }
}

</script>