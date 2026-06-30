package com.ayurvedahome

import com.ayurvedahome.domain.content.AyurvedaCatalog
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UtilityTest {
    @Test fun categories_count_8() {
        assertEquals(8, AyurvedaCatalog.categories.size)
    }

    @Test fun each_category_has_remedies() {
        AyurvedaCatalog.categories.forEach { category ->
            val count = AyurvedaCatalog.remediesForCategory(category.id).size
            assertTrue("$category has remedies", count in 5..8)
        }
    }

    @Test fun search_finds_tulsi() {
        assertTrue(AyurvedaCatalog.search("tulsi").isNotEmpty())
    }
}
