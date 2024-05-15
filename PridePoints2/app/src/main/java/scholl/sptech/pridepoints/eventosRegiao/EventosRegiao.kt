package scholl.sptech.pridepoints.eventosRegiao

import java.time.LocalDate


data class EventosRegiao(val id:Long,
                         val nome:String?= null,
                         val imgEvento:String? = null,
                         val descricaoEvento:String? = null,
                         val dtEvento:LocalDate? = null)
