package nl.toefel.springbootcoroutines
data class OverviewDto(
        val posts: List<Post>,
        val todos: List<Todo>,
        val todo1 : Todo,
)