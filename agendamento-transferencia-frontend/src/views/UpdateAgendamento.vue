<template>
    <main>
            <Navbar/>
            <div class="my-5">
                <div class="mx-auto w-300" style="max-width: 100;">
                    <h2 class="text-center mb-3">Editar Agendamento</h2>
                    <form @submit.prevent="updateAgendamento">
                     <!-- contaOrigem -->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="contaOrigem" class="form-label">Conta de Origem</label>
                            <input v-model="agendamento.contaOrigem" type = "number" maxlength = "6" name="contaOrigem" id="contaOrigem" class="form-control" placeholder="xxxxxx" oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);" required />
                        </div>
                    </div>
                    <!-- contaDestino -->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="contaDestino" class="form-label">Conta de Destino</label>
                            <input v-model="agendamento.contaDestino" type = "number" maxlength = "6" name="contaDestino" id="contaDestino" class="form-control" placeholder="xxxxxx" oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);" required />
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
                            <input type="text" name="dataTransferencia" id="dataTransferencia" class="form-control" placeholder="dd-mm-aaaa"  v-mask="'##-##-####'" required v-model="agendamento.dataTransferencia">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 form-group">
                            <input type="submit" value="Submit" class="btn btn-primary w-100">
                        </div>

                    </div>
                    </form>
                </div>
            </div>
        
    </main>
</template>

<script>

import Navbar from '../components/Navbar.vue'

export default {
    name: 'UpdateAgendamentos',
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

    beforeMount(){
            this.getAgendamento()
        },

    methods: {
        getAgendamento(){
            fetch(`http://localhost:8080/api/agendamento-transferencia/${this.$route.params.id}`)
            .then(res => res.json())
            .then(data => {
                this.agendamento = data;
                console.log(this.agendamento)
            })
        },

        updateAgendamento(){
            fetch(`http://localhost:8080/api/agendamento-transferencia/edit`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.agendamento)
            })
            .then(data => {
                console.log(this.data)
                if (!data.ok) {
                    const error = (data && data.message) || data.statusText;
                    return Promise.reject(error);
                }
                this.$router.push("/")
                this.$toast.success(`Adicionado com sucesso!`, {
                        position: "top-right",
                        });
                    }).catch(error => {
                this.errorMessage = error;
                this.$toast.error("Erro ao salvar agendamento para transferência: Transferência inadequada às regras.", {
                        position: "top-right",
                        });
            });s
        }
    },
    beforeMount(){
        this.getAgendamento()
    }
}

</script>