package com.example.amanshop.presentation.components

import com.example.amanshop.R
import java.util.UUID

data class ProductDetailData (
    val id : String = UUID.randomUUID().toString(),
    val image : List<Int>,
    val title : String,
    val price : String,
    val postedDate : String,
    val description : String,
    val sellerData : SellerDataItem,
    val definedDescription : List<Pair<String,String>>,
    val deliveryType : String,
    val productLocation : String,
    val productCity : String = "Addis Ababa",
    val numberOfView : Int,
    val condition : String = "Brand New",
)


val ProductDetailDataList = mutableListOf(
    ProductDetailData(
        image = listOf(R.drawable.rcd_laptop),
        title = "Brand new laptop 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "12/12/2024",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[0],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 115
    ),
    ProductDetailData(
        image = listOf(
            R.drawable.rcd_car,
            R.drawable.rcd_car1,
            R.drawable.rcd_car2,
            R.drawable.rcd_car3,
        ),
        title = "Brand new Lamborghini 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "11/12/2025",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[1],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 113
    ),
    ProductDetailData(
        image = listOf(
            R.drawable.rcd_table,
        ),
        title = "Brand new table 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "20/12/2025",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[2],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 110
    ),
    ProductDetailData(
        image = listOf(
            R.drawable.rcd_home,
        ),
        title = "Brand new home 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "12/10/2025",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[0],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 110
    ),
    ProductDetailData(
        image = listOf(
            R.drawable.rcd_phone,

        ),
        title = "Brand new phone 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "12/12/2025",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[1],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 110
    ),
    ProductDetailData(
        image = listOf(
            R.drawable.rcd_factory,
        ),
        title = "Brand new factory 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "12/12/2025",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[2],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 110
    ),
    ProductDetailData(
        image = listOf(
            R.drawable.rcd_grinder,
        ),
        title = "Brand new grinder 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "12/12/2025",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[2],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 110
    ),
    ProductDetailData(
        image = listOf(
            R.drawable.rcd_jewellery,
        ),
        title = "Brand new jewellery 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "12/12/2025",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[0],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 110
    ),
    ProductDetailData(
        image = listOf(
            R.drawable.trd_spark_phone,
        ),
        title = "Brand new Spark phone 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "12/12/2025",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[1],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 110
    ),
    ProductDetailData(
        image = listOf(
            R.drawable.trd_ps4,
        ),
        title = "Brand new PS$ 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "12/12/2025",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[2],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 110
    ),
    ProductDetailData(
        image = listOf(R.drawable.trd_canon_camera,),
        title = "Brand new Camera 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "12/12/2025",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[0],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 110
    ),
    ProductDetailData(
        image = listOf(R.drawable.trd_white_jacket),
        title = "Brand new Jacket 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "12/12/2025",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[1],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 110
    ),
    ProductDetailData(
        image = listOf(R.drawable.trd_shirt),
        title = "Brand new shirt 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "12/12/2025",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[2],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 110
    ),
    ProductDetailData(
        image = listOf(R.drawable.trd_perpel_dress),
        title = "Brand new Dress 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "12/12/2025",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[0],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 110
    ),
    ProductDetailData(
        image = listOf(R.drawable.trd_leather_shoes),
        title = "Brand new leather shoes 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "12/12/2025",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[1],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 110
    ),
    ProductDetailData(
        image = listOf(R.drawable.trd_hand_watch),
        title = "Brand new hand watch 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "12/12/2025",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[2],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 110
    ),
    ProductDetailData(
        image = listOf(R.drawable.trd_trouser),
        title = "Brand new trouser 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "12/12/2025",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[0],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 110
    ),
    ProductDetailData(
        image = listOf(R.drawable.trd_nike_shoes),
        title = "Brand new nike shoes shoes 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "12/12/2025",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[1],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 110
    ),
    ProductDetailData(
        image = listOf(
            R.drawable.trd_coffee_dress,
        ),
        title = "Brand new dress shoes 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "12/12/2025",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[2],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 110
    ),
    ProductDetailData(
        image = listOf(
            R.drawable.trd_jordan_shoe,
        ),
        title = "Brand new jordan shoes shoes 2025 made in USA high speed and durability ",
        price = "1,000",
        postedDate = "12/12/2025",
        description = "Brand: Lamborghini\n" +
                "Year: 2024\n" +
                "Engine: V12 / V10 (depending on model)\n" +
                "Color: Green\n" +
                "Horsepower: 630-780 HP (depending on model)\n" +
                "Transmission: 7-Speed Dual-Clutch Automatic\n" +
                "Top Speed: 211 mph (Aventador)\n" +
                "0-60 mph: 2.9 seconds (Aventador)\n" +
                "\n" +
                "Lamborghini's 2024 models deliver a combination of unparalleled performance and cutting-edge design, continuing the legacy of luxury and innovation the brand is known for.",

        sellerData = SellersInfoList[0],
        definedDescription = listOf(
            "Make" to "Lamborghini",
            "Model" to "L123-21",
            "Year" to "2025",
            "Color" to "Bright Green",
            "Engine" to "V12 / V10",
            "Driving" to "Self Driving"
        ),
        deliveryType = "Free Delivery",
        productLocation = "Sebeta",
        numberOfView = 110
    ),
)