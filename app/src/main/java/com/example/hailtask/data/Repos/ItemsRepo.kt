package com.example.hailtask.data.Repos
import com.example.hailtask.data.Api.ItemsHelper
import com.example.hailtask.data.Api.ItemsInterface


class ItemsRepo {
    suspend fun getItems() = ItemsHelper.api.getItems("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2hhaWwud2Vic2l0ZS9hcGkvbG9naW4iLCJpYXQiOjE3MDk5ODYzMzAsImV4cCI6MTkzMDgyNDczMCwibmJmIjoxNzA5OTg2MzMwLCJqdGkiOiJCalJTUnM4UHNidDh1NUx3Iiwic3ViIjoiODIiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.7oaMzVDiV9nk2UR3XTH5jA93bIxvOx9hsabyHa7tEoo")
}