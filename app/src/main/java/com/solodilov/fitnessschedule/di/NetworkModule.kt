package com.solodilov.fitnessschedule.di

import com.solodilov.fitnessschedule.data.datasource.network.FitnessKitApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

	private companion object {

		const val BASE_URL = "https://olimpia.fitnesskit-admin.ru/"
	}

	@Provides
	fun provideGsonConverterFactory(): GsonConverterFactory =
		GsonConverterFactory.create()

	@Provides
	@Singleton
	fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
	): Retrofit =
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(gsonConverterFactory)
			.build()

	@Provides
	@Singleton
	fun provideFitnessKitApi(
		retrofit: Retrofit,
	): FitnessKitApi =
		retrofit.create(FitnessKitApi::class.java)
}