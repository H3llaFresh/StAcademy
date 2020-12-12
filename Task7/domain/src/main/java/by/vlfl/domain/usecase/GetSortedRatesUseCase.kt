package by.vlfl.domain.usecase

import by.vlfl.domain.model.Rate
import by.vlfl.domain.repository.IRatesRepository

class GetSortedRatesUseCase(
        private val repository: IRatesRepository
    ) {
        fun execute(): List<Rate> {
            return repository.getRates().sortedBy { it.curName }
        }
    }