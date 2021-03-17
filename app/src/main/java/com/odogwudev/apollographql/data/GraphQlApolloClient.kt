package com.odogwudev.apollographql.data

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Input
import com.odogwudev.apollographql.data.common.BASE_URL
import com.odogwudev.apollographql.GetCharactersQuery

object GraphQlApolloClient {

    private fun apolloClient(): ApolloClient =
        ApolloClient.builder().serverUrl(BASE_URL).build()

    fun getCharacters(page: Int): ApolloQueryCall<GetCharactersQuery.Data> =
        apolloClient().query(GetCharactersQuery(Input.optional(page)))
}