package hotkitchen.data.repository.database.exposed.entity

import hotkitchen.data.repository.database.exposed.table.CategoryTable
import hotkitchen.domain.model.Category
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CategoryEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CategoryEntity>(CategoryTable)

    var title: String by CategoryTable.title
    var description: String by CategoryTable.description

    fun toCategory(): Category {
        return Category(
            categoryId = id.value,
            title = title,
            description = description
        )
    }
}