<template>
    <main>

        <Navbar/>
        <div class="my-5">
            <div class="mx-auto w-300" style="max-width: 100%;">
                <h2 class="text-center mb-3">Adicionar Agendamento</h2>
                <form @submit.prevent="addAgendamentos">
                    <!-- contaOrigem -->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="contaOrigem" class="form-label">Conta de Origem</label>
                            <input type="text" name="contaOrigem" id="contaOrigem" class="form-control" placeholder="xxxxxx" required v-model="agendamento.contaOrigem">
                        </div>
                    </div>
                    <!-- contaDestino -->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="contaDestino" class="form-label">Conta de Destino</label>
                            <input type="text" name="contaDestino" id="contaDestino" class="form-control" placeholder="xxxxxx" required v-model="agendamento.contaDestino">
                        </div>
                    </div>
                    <!-- valorTransferencia --> 
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="valorTransferencia" class="form-label">Valor para Tranferência</label>
                            <input type="number" name="valorTransferencia" id="valorTransferencia" class="form-control" placeholder="0.00" required v-model="agendamento.valorTransferencia">
                        </div>
                    </div>
                    <!-- dataTransferencia -->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="dataTransferencia" class="form-label">Data para Tranferência</label>
                            <input type="text" name="dataTransferencia" id="dataTransferencia" class="form-control" placeholder="dd-mm-aaaa" required v-model="agendamento.dataTransferencia">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 form-group">
                            <input type="submit" value="Adicionar" class="btn btn-primary w-100">
                        </div>

                    </div>
                </form>
            </div>
        </div>

    </main>
</template>


<script type="text/javascript">

import Navbar from '../components/Navbar.vue'

export default {
    name: 'AddAgendamentos',
    components : {
        Navbar
    },

    data(){
        return {
            agendamento: {
                contaOrigem: '',
                contaDestino: '',
                valorTransferencia: '',
                dataTransferencia: ''
            }
        }
    },

    methods: {
        addAgendamentos(){
            fetch('http://localhost:8080/api/agendamento-transferencia/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*'
                },
                body: JSON.stringify(this.agendamento)
            })
            .then(data => {
                console.log(data.status)
                this.$router.push("/")
            })
        }
    },
}
</script>


