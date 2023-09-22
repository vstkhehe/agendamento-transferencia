<template>
    <main>
        <Navbar />

        <!-- table -->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="text-center">View Agendamentos</h1>

                    <a href="/add" class="btn btn-primary"> Add Agendamentos</a>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Conta de Origem</th>
                                <th scope="col">Conta de Destino</th>
                                <th scope="col">Valor da Transferência</th>
                                <th scope="col">Valor da Taxa de Tranferência</th>
                                <th scope="col">Valor Total da Tranferência</th>
                                <th scope="col">Data da Transferência</th>
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
    name: 'ViewAgendamentos',
    componentes : {
        Navbar
    },
    data(){
        return {
            agendamentos: []
        }
    },

    methods: {
        getAgendamentos(){
            fetch('http://localhost:8080/api/agendamento-transferencia/agendamentos')
            .then(res => res.json())
            .then(data => {
                this.agendamentos = data
                console.log(data)
            })
        },
        deleteAgendamentos(id){
            fetch('http://localhost:8080/api/agendamento-transferencia/${id}', 
            {
                methos: 'DELETE'
            })
            .then(data => {
                console.log(data)
                this.getAgendamentos()
            })
        }
    }
}

</script>