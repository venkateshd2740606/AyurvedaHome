package com.ayurvedahome.domain.content

data class AyurvedaCategory(
    val id: String,
    val name: String,
    val description: String
)

data class AyurvedaRemedy(
    val id: String,
    val categoryId: String,
    val name: String,
    val hindiName: String? = null,
    val ingredients: List<String>,
    val steps: List<String>,
    val usageNote: String
)

object AyurvedaCatalog {
    const val DISCLAIMER =
        "Traditional remedies for information only. Consult doctor for serious conditions. Not a substitute for medical treatment."

    val categories: List<AyurvedaCategory> = listOf(
        AyurvedaCategory("cold_cough", "Cold & Cough", "Soothing remedies for seasonal congestion"),
        AyurvedaCategory("digestion", "Digestion", "Gentle support for stomach comfort"),
        AyurvedaCategory("headache", "Headache", "Calming practices for mild headaches"),
        AyurvedaCategory("skin", "Skin", "Natural care for common skin concerns"),
        AyurvedaCategory("hair", "Hair", "Nourishing treatments for hair health"),
        AyurvedaCategory("sleep", "Sleep", "Relaxing routines for better rest"),
        AyurvedaCategory("immunity", "Immunity", "Daily wellness habits for strength"),
        AyurvedaCategory("joint_pain", "Joint Pain", "Warmth and herbs for joint comfort")
    )

    val remedies: List<AyurvedaRemedy> = listOf(
        // Cold & Cough (6)
        AyurvedaRemedy(
            "cc1", "cold_cough", "Tulsi Ginger Tea", "तुलसी अदरक की चाय",
            listOf("8–10 tulsi leaves", "1 inch fresh ginger", "1 cup water", "Honey (optional)"),
            listOf("Boil water with crushed ginger for 5 minutes", "Add tulsi leaves and simmer 2 minutes", "Strain and add honey when lukewarm"),
            "Sip warm 2–3 times daily. Avoid if allergic to tulsi."
        ),
        AyurvedaRemedy(
            "cc2", "cold_cough", "Turmeric Milk", "हल्दी वाला दूध",
            listOf("1 cup milk", "½ tsp turmeric powder", "Pinch of black pepper", "Honey to taste"),
            listOf("Warm milk without boiling over", "Stir in turmeric and pepper", "Sweeten with honey"),
            "Drink before bed for soothing warmth. Not for dairy allergy."
        ),
        AyurvedaRemedy(
            "cc3", "cold_cough", "Honey Lemon Warm Water", "शहद नींबू पानी",
            listOf("1 tbsp honey", "Juice of half lemon", "1 cup warm water"),
            listOf("Mix warm water and lemon", "Stir in honey until dissolved"),
            "Take in the morning on an empty stomach. Children under 1 should not have honey."
        ),
        AyurvedaRemedy(
            "cc4", "cold_cough", "Steam with Eucalyptus", "यूकेलिप्टस भाप",
            listOf("Bowl of hot water", "2–3 drops eucalyptus oil (optional)", "Towel"),
            listOf("Pour hot water in a bowl", "Add oil if using", "Cover head with towel and inhale steam 5–8 minutes"),
            "Use carefully to avoid burns. Keep eyes closed during steam."
        ),
        AyurvedaRemedy(
            "cc5", "cold_cough", "Mulethi (Licorice) Decoction", "मुलेठी काढ़ा",
            listOf("1 tsp mulethi root powder or stick", "1 cup water"),
            listOf("Boil mulethi in water for 8–10 minutes", "Strain and cool slightly"),
            "Gargle or sip warm twice daily. Avoid prolonged use if you have high blood pressure."
        ),
        AyurvedaRemedy(
            "cc6", "cold_cough", "Ajwain Potli Inhalation", "अजवाइन पोटली",
            listOf("1 tbsp ajwain (carom seeds)", "Clean cloth pouch or handkerchief"),
            listOf("Dry-roast ajwain until aromatic", "Tie in cloth to make a small potli", "Inhale warm fumes near nose"),
            "Relieves nasal stuffiness. Do not apply directly on skin if sensitive."
        ),
        // Digestion (6)
        AyurvedaRemedy(
            "dg1", "digestion", "Jeera (Cumin) Water", "जीरा पानी",
            listOf("1 tsp cumin seeds", "1 cup water"),
            listOf("Soak cumin overnight or boil 5 minutes", "Strain and drink lukewarm"),
            "Drink after meals to support digestion."
        ),
        AyurvedaRemedy(
            "dg2", "digestion", "Ajwain with Warm Water", "अजवाइन पानी",
            listOf("½ tsp ajwain", "1 cup warm water", "Pinch of salt (optional)"),
            listOf("Chew ajwain lightly", "Follow with warm water"),
            "Use after heavy meals for bloating relief."
        ),
        AyurvedaRemedy(
            "dg3", "digestion", "Saunf (Fennel) Tea", "सौंफ की चाय",
            listOf("1 tsp fennel seeds", "1 cup water"),
            listOf("Boil fennel in water for 5 minutes", "Strain and sip warm"),
            "Enjoy after lunch or dinner. Mild and family-friendly."
        ),
        AyurvedaRemedy(
            "dg4", "digestion", "Triphala Warm Water", "त्रिफला पानी",
            listOf("½ tsp triphala powder", "1 cup warm water"),
            listOf("Soak triphala in warm water for 2 hours or overnight", "Strain and drink on empty stomach in morning"),
            "Start with small amount. Consult practitioner if pregnant."
        ),
        AyurvedaRemedy(
            "dg5", "digestion", "Buttermilk with Curry Leaves", "कढ़ी पत्ता छाछ",
            listOf("1 cup fresh buttermilk", "6–8 curry leaves", "Pinch of roasted cumin"),
            listOf("Blend curry leaves into buttermilk or temper leaves in ghee and mix", "Add cumin and salt to taste"),
            "Drink at lunch time in hot weather."
        ),
        AyurvedaRemedy(
            "dg6", "digestion", "Ginger-Lemon Digestive Shot", "अदरक नींबू शॉट",
            listOf("1 tsp ginger juice", "1 tsp lemon juice", "Pinch of rock salt"),
            listOf("Mix all ingredients", "Dilute with 2 tbsp warm water if too strong"),
            "Take before meals once daily. Avoid on active acidity."
        ),
        // Headache (5)
        AyurvedaRemedy(
            "hd1", "headache", "Peppermint Temple Massage", "पुदीना मालिश",
            listOf("1–2 drops peppermint oil", "1 tsp coconut oil"),
            listOf("Dilute peppermint in coconut oil", "Gently massage temples and forehead"),
            "Avoid contact with eyes. Patch-test if skin is sensitive."
        ),
        AyurvedaRemedy(
            "hd2", "headache", "Lavender Compress", "लैवेंडर कंप्रेस",
            listOf("Few drops lavender oil", "Warm water", "Soft cloth"),
            listOf("Add oil to warm water", "Soak cloth, wring excess", "Place on forehead 10 minutes"),
            "Rest in a quiet room while using compress."
        ),
        AyurvedaRemedy(
            "hd3", "headache", "Coriander Seed Tea", "धनिया की चाय",
            listOf("1 tsp coriander seeds", "1 cup water", "Honey optional"),
            listOf("Boil seeds for 5–7 minutes", "Strain and drink warm"),
            "Helpful for tension-type discomfort. Stay hydrated."
        ),
        AyurvedaRemedy(
            "hd4", "headache", "Brahmi Scalp Oil Rub", "ब्राह्मी तेल",
            listOf("1 tsp brahmi or coconut oil", "Warm water towel"),
            listOf("Warm oil slightly", "Massage scalp gently for 5 minutes", "Rest with warm towel"),
            "Best done in evening. Seek care for sudden severe headache."
        ),
        AyurvedaRemedy(
            "hd5", "headache", "Pranayama — Anulom Vilom", "अनुलोम विलोम",
            listOf("Quiet space", "Comfortable seated posture"),
            listOf("Close right nostril, inhale left", "Close left, exhale right", "Repeat 5–10 cycles slowly"),
            "Practice daily for stress-related headaches. Stop if dizzy."
        ),
        // Skin (6)
        AyurvedaRemedy(
            "sk1", "skin", "Neem Face Wash Water", "नीम का पानी",
            listOf("Handful neem leaves", "2 cups water"),
            listOf("Boil neem leaves 10 minutes", "Cool and strain", "Splash or rinse face"),
            "Use once daily. Patch-test for sensitive skin."
        ),
        AyurvedaRemedy(
            "sk2", "skin", "Aloe Vera Gel Application", "एलोवेरा जेल",
            listOf("Fresh aloe leaf or pure aloe gel"),
            listOf("Extract clear gel from leaf", "Apply thin layer on clean skin", "Leave 15 minutes, rinse"),
            "Soothes mild irritation. Use fresh gel when possible."
        ),
        AyurvedaRemedy(
            "sk3", "skin", "Turmeric Besan Face Pack", "हल्दी बेसन फेस पैक",
            listOf("1 tbsp gram flour (besan)", "Pinch turmeric", "Rose water or milk"),
            listOf("Mix to paste consistency", "Apply for 10 minutes", "Rinse gently"),
            "Once or twice weekly. May temporarily tint fair skin."
        ),
        AyurvedaRemedy(
            "sk4", "skin", "Coconut Oil Moisturizer", "नारियल तेल",
            listOf("Pure cold-pressed coconut oil"),
            listOf("Warm few drops between palms", "Massage lightly on dry areas"),
            "Best after bath on slightly damp skin."
        ),
        AyurvedaRemedy(
            "sk5", "skin", "Sandalwood Paste", "चंदन लेप",
            listOf("1 tsp sandalwood powder", "Rose water"),
            listOf("Mix powder with rose water to paste", "Apply on affected area", "Wash after drying"),
            "Cooling for mild heat rash. Use authentic chandan powder."
        ),
        AyurvedaRemedy(
            "sk6", "skin", "Cucumber Cooling Rub", "खीरा रब",
            listOf("Fresh cucumber", "Cotton pad"),
            listOf("Grate or blend cucumber", "Apply juice with cotton", "Rest 10 minutes and rinse"),
            "Refreshing for sun-exposed skin."
        ),
        // Hair (5)
        AyurvedaRemedy(
            "hr1", "hair", "Amla Hair Rinse", "आंवला रिंस",
            listOf("2 tbsp amla powder", "2 cups water"),
            listOf("Boil amla powder 10 minutes", "Cool and strain", "Use as final rinse after shampoo"),
            "Weekly use may add shine. Avoid if scalp is broken."
        ),
        AyurvedaRemedy(
            "hr2", "hair", "Bhringraj Oil Massage", "भृंगराज तेल",
            listOf("2 tbsp bhringraj oil", "Warm towel"),
            listOf("Warm oil slightly", "Massage scalp 5–10 minutes", "Leave 30 minutes before wash"),
            "Traditional nourishing routine 1–2 times weekly."
        ),
        AyurvedaRemedy(
            "hr3", "hair", "Fenugreek Hair Mask", "मेथी हेयर मास्क",
            listOf("2 tbsp fenugreek seeds", "Water"),
            listOf("Soak seeds overnight", "Grind to paste", "Apply to scalp 20 minutes", "Wash thoroughly"),
            "May help dryness. Rinse well to avoid smell."
        ),
        AyurvedaRemedy(
            "hr4", "hair", "Coconut Curry Leaf Oil", "नारियल कढ़ी पत्ता तेल",
            listOf("½ cup coconut oil", "Handful curry leaves"),
            listOf("Heat oil with curry leaves until leaves crisp", "Cool and strain", "Store and use for scalp massage"),
            "Homemade oil; store in clean jar up to 2 weeks."
        ),
        AyurvedaRemedy(
            "hr5", "hair", "Shikakai Gentle Wash", "शिकाकाई वॉश",
            listOf("2 tbsp shikakai powder", "Warm water"),
            listOf("Soak powder 2 hours", "Strain and use as mild hair wash"),
            "Mild alternative to harsh shampoo."
        ),
        // Sleep (5)
        AyurvedaRemedy(
            "sl1", "sleep", "Warm Milk with Nutmeg", "जायफल वाला दूध",
            listOf("1 cup milk", "Pinch nutmeg powder", "Honey optional"),
            listOf("Warm milk", "Stir in nutmeg", "Sweeten if desired"),
            "Drink 30 minutes before bed. Use tiny pinch of nutmeg only."
        ),
        AyurvedaRemedy(
            "sl2", "sleep", "Chamomile Bedtime Tea", "कैमोमाइल चाय",
            listOf("1 tsp chamomile flowers or tea bag", "1 cup hot water"),
            listOf("Steep 5 minutes", "Strain and sip slowly"),
            "Avoid if allergic to ragweed family plants."
        ),
        AyurvedaRemedy(
            "sl3", "sleep", "Foot Massage with Sesame Oil", "तिल का तेल पैर मालिश",
            listOf("1 tbsp warm sesame oil"),
            listOf("Sit comfortably", "Massage soles and ankles 5–10 minutes"),
            "Calming nightly ritual. Wipe excess oil before bed."
        ),
        AyurvedaRemedy(
            "sl4", "sleep", "Lavender Pillow Mist", "लैवेंडर मिस्ट",
            listOf("Few drops lavender oil", "Water in spray bottle"),
            listOf("Add drops to water", "Lightly mist pillow from distance"),
            "Use sparingly. Ensure good room ventilation."
        ),
        AyurvedaRemedy(
            "sl5", "sleep", "Jatamansi Calming Tea", "जटामांसी चाय",
            listOf("¼ tsp jatamansi powder", "1 cup water", "Honey"),
            listOf("Simmer powder 5 minutes", "Strain and cool slightly"),
            "Traditional herb for restlessness. Consult doctor if on sedatives."
        ),
        // Immunity (6)
        AyurvedaRemedy(
            "im1", "immunity", "Chyawanprash Morning Spoon", "च्यवनप्राश",
            listOf("1 tsp chyawanprash", "Warm milk or water optional"),
            listOf("Take directly or mix in warm milk"),
            "Daily morning habit in cooler months. Check sugar content if diabetic."
        ),
        AyurvedaRemedy(
            "im2", "immunity", "Golden Turmeric Latte", "गोल्डन मिल्क",
            listOf("1 cup milk", "½ tsp turmeric", "Pinch cinnamon", "Pinch black pepper"),
            listOf("Warm all ingredients together", "Stir well and serve"),
            "Evening or morning wellness drink."
        ),
        AyurvedaRemedy(
            "im3", "immunity", "Tulsi Immunity Tea", "तुलसी इम्युनिटी टी",
            listOf("10 tulsi leaves", "2 cups water", "Ginger slice", "Honey"),
            listOf("Boil water with ginger", "Add tulsi, simmer 3 minutes", "Strain and sweeten"),
            "Drink 1–2 cups daily during season change."
        ),
        AyurvedaRemedy(
            "im4", "immunity", "Amla Murabba", "आंवला मुरब्बा",
            listOf("1 piece amla murabba (store-bought or homemade)"),
            listOf("Eat one piece with breakfast"),
            "Vitamin C rich tradition. Moderate sugar intake."
        ),
        AyurvedaRemedy(
            "im5", "immunity", "Giloy Juice", "गिलोय रस",
            listOf("2 tsp giloy juice", "1 cup water"),
            listOf("Dilute juice in water", "Drink on empty stomach"),
            "Popular seasonal tonic. Consult doctor if autoimmune condition."
        ),
        AyurvedaRemedy(
            "im6", "immunity", "Sun Salutation Routine", "सूर्य नमस्कार",
            listOf("Open space or yoga mat", "Comfortable clothing"),
            listOf("Practice 6–12 rounds slowly", "Coordinate breath with movement", "Cool down with rest"),
            "Builds daily vitality. Modify poses if you have injuries."
        ),
        // Joint Pain (6)
        AyurvedaRemedy(
            "jp1", "joint_pain", "Warm Sesame Oil Massage", "तिल तेल मालिश",
            listOf("2 tbsp sesame oil", "Optional pinch camphor"),
            listOf("Warm oil", "Massage affected joints 10 minutes", "Cover with warm cloth"),
            "Best before bath. Avoid broken skin."
        ),
        AyurvedaRemedy(
            "jp2", "joint_pain", "Ajwain Potli Compress", "अजवाइन पोटली",
            listOf("2 tbsp ajwain", "Cloth pouch"),
            listOf("Dry-roast ajwain", "Fill pouch and tie", "Warm on pan briefly", "Apply on joints"),
            "Check temperature before skin contact."
        ),
        AyurvedaRemedy(
            "jp3", "joint_pain", "Turmeric Ginger Paste", "हल्दी अदरक लेप",
            listOf("1 tsp turmeric", "1 tsp ginger juice", "Warm water"),
            listOf("Mix to paste", "Apply thin layer on joint", "Wash after 20 minutes"),
            "May stain skin temporarily. Patch-test first."
        ),
        AyurvedaRemedy(
            "jp4", "joint_pain", "Epsom Salt Soak", "सेंधा नमक सोख",
            listOf("2 tbsp Epsom salt", "Basin of warm water"),
            listOf("Dissolve salt in warm water", "Soak feet or hands 15 minutes"),
            "Relaxing after long day. Not for open wounds."
        ),
        AyurvedaRemedy(
            "jp5", "joint_pain", "Dashmool Decoction", "दशमूल काढ़ा",
            listOf("1 tsp dashmool powder", "2 cups water"),
            listOf("Boil until half remains", "Strain and drink warm"),
            "Traditional joint-support decoction. Use under Ayurvedic guidance if on medication."
        ),
        AyurvedaRemedy(
            "jp6", "joint_pain", "Gentle Joint Rotation Yoga", "संधि योग",
            listOf("Yoga mat", "Quiet space"),
            listOf("Rotate wrists, ankles, knees slowly", "10 reps each direction", "Never force painful range"),
            "Daily mobility practice. Stop if sharp pain occurs."
        )
    )

    fun category(id: String): AyurvedaCategory? = categories.find { it.id == id }

    fun remedy(id: String): AyurvedaRemedy? = remedies.find { it.id == id }

    fun remediesForCategory(categoryId: String): List<AyurvedaRemedy> =
        remedies.filter { it.categoryId == categoryId }

    fun search(query: String): List<AyurvedaRemedy> {
        val q = query.trim().lowercase()
        if (q.isEmpty()) return emptyList()
        return remedies.filter { remedy ->
            remedy.name.lowercase().contains(q) ||
                remedy.hindiName?.lowercase()?.contains(q) == true ||
                remedy.ingredients.any { it.lowercase().contains(q) } ||
                category(remedy.categoryId)?.name?.lowercase()?.contains(q) == true
        }
    }
}
