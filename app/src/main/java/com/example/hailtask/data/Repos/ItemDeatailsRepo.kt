package com.example.hailtask.data.Repos

import com.example.hailtask.data.Api.ItemDetails.ItemDetailsHelper
import com.example.hailtask.data.Api.Items.ItemsHelper
import com.example.hailtask.util.Constants


class ItemDeatailsRepo {
    suspend fun getItemDet(id:Int) = ItemDetailsHelper.api.getItemDet(Constants.auth, Constants.apiKey, id)
}