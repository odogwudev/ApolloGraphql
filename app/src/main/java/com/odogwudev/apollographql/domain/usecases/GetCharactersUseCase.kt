package com.odogwudev.apollographql.domain.usecases

import com.odogwudev.apollographql.domain.repository.AppRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend operator fun invoke(request: Int) = appRepository.getCharacters(request)
}