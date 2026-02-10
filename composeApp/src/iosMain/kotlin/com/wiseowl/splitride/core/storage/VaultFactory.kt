package com.wiseowl.splitride.core.storage

import com.liftric.kvault.KVault

fun getVaultFactory() = KVault("storage_manager_vault", "split_ride")