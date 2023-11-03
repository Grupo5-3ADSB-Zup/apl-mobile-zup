package school.sptech.zup.data.model

import school.sptech.zup.domain.model.FeedRequest

data class FeedResponse(
    val id: Long,
    val titulo: String,
    val descricao: String,
    val link: String,
    val emissora: String,
    val dtNoticia: String,
    val fotoNoticia: ByteArray
) : List<FeedRequest> {
    override val size: Int
        get() = TODO("Not yet implemented")

    override fun contains(element: FeedRequest): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<FeedRequest>): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): FeedRequest {
        TODO("Not yet implemented")
    }

    override fun indexOf(element: FeedRequest): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<FeedRequest> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: FeedRequest): Int {
        TODO("Not yet implemented")
    }

    override fun listIterator(): ListIterator<FeedRequest> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): ListIterator<FeedRequest> {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<FeedRequest> {
        TODO("Not yet implemented")
    }
}
