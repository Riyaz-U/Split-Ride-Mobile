package com.wiseowl.splitride.core.storage

import android.content.Context
import com.liftric.kvault.KVault

fun getVaultFactory(context: Context): KVault = KVault(context, "split_ride")