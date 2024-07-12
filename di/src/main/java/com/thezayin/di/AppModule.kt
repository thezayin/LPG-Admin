package com.thezayin.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.thezayin.addproducts.data.AddProductRepositoryImpl
import com.thezayin.addproducts.domain.repository.AddProductRepository
import com.thezayin.addproducts.domain.usecase.AddProductUseCase
import com.thezayin.addproducts.domain.usecase.AddProductUseCaseImpl
import com.thezayin.addproducts.domain.usecase.UploadImage
import com.thezayin.addproducts.domain.usecase.UploadImageImpl
import com.thezayin.addproducts.presentation.AddProductViewModel
import com.thezayin.home.data.AdminOptionMenuRepositoryImpl
import com.thezayin.home.domain.repository.AdminOptionMenuRepository
import com.thezayin.home.domain.usecase.AdminOptionMenuUseCase
import com.thezayin.home.domain.usecase.AdminOptionMenuUseCaseImpl
import com.thezayin.home.presentation.AdminHomeViewModel
import com.thezayin.orders.data.FetchOrdersRepositoryImpl
import com.thezayin.orders.data.OrderStatusRepositoryImpl
import com.thezayin.orders.domain.repository.FetchOrdersRepository
import com.thezayin.orders.domain.repository.OrderStatusRepository
import com.thezayin.orders.domain.usecase.FetchOrders
import com.thezayin.orders.domain.usecase.FetchOrdersImpl
import com.thezayin.orders.domain.usecase.GetStatusList
import com.thezayin.orders.domain.usecase.GetStatusListImpl
import com.thezayin.orders.domain.usecase.UpdateOrderStatus
import com.thezayin.orders.domain.usecase.UpdateOrderStatusImpl
import com.thezayin.adminorders.presentation.FetchOrdersViewModel
import com.thezayin.productdetails.data.ProDetailsRepositoryImpl
import com.thezayin.productdetails.domain.repository.ProDetailsRepository
import com.thezayin.productdetails.domain.usecase.DeleteAdminProduct
import com.thezayin.productdetails.domain.usecase.DeleteAdminProductImpl
import com.thezayin.productdetails.domain.usecase.UpdateAdminProduct
import com.thezayin.productdetails.domain.usecase.UpdateAdminProductIml
import com.thezayin.productdetails.domain.usecase.UpdateImage
import com.thezayin.productdetails.domain.usecase.UpdateImageImpl
import com.thezayin.productdetails.presentation.AdminProDetailsViewModel
import com.thezayin.products.data.AdminProductRepositoryImpl
import com.thezayin.products.data.GetProductImagesRepositoryImpl
import com.thezayin.products.domain.repository.AdminProductRepository
import com.thezayin.products.domain.repository.GetProductImagesRepository
import com.thezayin.products.domain.usecase.GetAdminProduct
import com.thezayin.products.domain.usecase.GetAdminProductImpl
import com.thezayin.products.domain.usecase.GetProductImages
import com.thezayin.products.domain.usecase.GetProductImagesImpl
import com.thezayin.products.presentation.AdminProductViewModel
import com.thezayin.framework.remote.RemoteConfig
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


/**
 * Admin Modules
 */
val appModule = module {
    single { Json { ignoreUnknownKeys = true } }
    singleOf(::RemoteConfig)
}

val getUserOrdersModule = module {
    viewModelOf(::FetchOrdersViewModel)
    factoryOf(::FetchOrdersRepositoryImpl) bind FetchOrdersRepository::class
    factoryOf(::OrderStatusRepositoryImpl) bind OrderStatusRepository::class
    factoryOf(::GetStatusListImpl) bind GetStatusList::class
    factoryOf(::UpdateOrderStatusImpl) bind UpdateOrderStatus::class
    factoryOf(::FetchOrdersImpl) bind FetchOrders::class
}

val productUpdateModule = module {
    viewModelOf(::AdminProDetailsViewModel)
    factoryOf(::ProDetailsRepositoryImpl) bind ProDetailsRepository::class
    factoryOf(::DeleteAdminProductImpl) bind DeleteAdminProduct::class
    factoryOf(::UpdateAdminProductIml) bind UpdateAdminProduct::class
    factoryOf(::UpdateImageImpl) bind UpdateImage::class
}

val productModule = module {
    viewModelOf(::AdminProductViewModel)
    factoryOf(::AdminProductRepositoryImpl) bind AdminProductRepository::class
    factoryOf(::GetProductImagesImpl) bind GetProductImages::class
    factoryOf(::GetProductImagesRepositoryImpl) bind GetProductImagesRepository::class
    factoryOf(::GetAdminProductImpl) bind GetAdminProduct::class
}


val homeModule = module {
    viewModelOf(::AdminHomeViewModel)
    factoryOf(::AdminOptionMenuRepositoryImpl) bind AdminOptionMenuRepository::class
    factoryOf(::AdminOptionMenuUseCaseImpl) bind AdminOptionMenuUseCase::class
}

val addProductModule = module {
    viewModelOf(::AddProductViewModel)
    single { FirebaseFirestore.getInstance() }
    single { FirebaseAuth.getInstance() }
    single { FirebaseStorage.getInstance() }
    factoryOf(::AddProductRepositoryImpl) bind AddProductRepository::class
    factoryOf(::AddProductUseCaseImpl) bind AddProductUseCase::class
    factoryOf(::UploadImageImpl) bind UploadImage::class
}

