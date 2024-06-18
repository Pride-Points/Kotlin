package com.adevinta.leku.geocoder.adapters.type

import android.location.Address
import com.adevinta.leku.lekuViewHolder
import com.adevinta.leku.geocoder.adapters.base.lekuSearchAdapter

abstract class AddressSearchAdapter<T : lekuViewHolder> : lekuSearchAdapter<T, Address>()
