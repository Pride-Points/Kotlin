package com.adevinta.leku.geocoder.adapters.type

import com.adevinta.leku.lekuViewHolder
import com.adevinta.leku.geocoder.PlaceSuggestion
import com.adevinta.leku.geocoder.adapters.base.lekuSearchAdapter

abstract class SuggestSearchAdapter<T : lekuViewHolder> : lekuSearchAdapter<T, PlaceSuggestion>()
