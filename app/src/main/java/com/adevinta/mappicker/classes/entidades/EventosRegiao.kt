package com.adevinta.mappicker.classes.entidades

import java.time.LocalDate


data class EventosRegiao(val id:Long,
                         val nome:String?= null,
                         val imgEvento:String? = null,
                         val descricaoEvento:String? = null)
