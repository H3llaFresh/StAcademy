package by.vlfl.domain.repository

import by.vlfl.domain.model.Rate

interface IRatesRepository {
    fun getRates(): List<Rate>
}