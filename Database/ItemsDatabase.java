package com.pharmavet.imperial.pharmavetdist.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.pharmavet.imperial.pharmavetdist.Database.Models.Company;
import com.pharmavet.imperial.pharmavetdist.Database.Models.DisplayItems;
import com.pharmavet.imperial.pharmavetdist.Database.Queries.CompanyDao;
import com.pharmavet.imperial.pharmavetdist.Database.Queries.DisplayItemsDao;
import com.pharmavet.imperial.pharmavetdist.Util.Constants.CompanyConstants;
import com.pharmavet.imperial.pharmavetdist.Util.ImageHandlers.ImageRetriever;

@Database(entities = {DisplayItems.class, Company.class}, version = 5)
public abstract class ItemsDatabase extends RoomDatabase {

    private static volatile ItemsDatabase INSTANCE;

    public abstract DisplayItemsDao displayItemsDao();

    public abstract CompanyDao companyDao();

    public static ItemsDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (ItemsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ItemsDatabase.class, "Products.db")
                            .addCallback(new Callback() {
                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                    new PopulateDbAsync(INSTANCE).execute();
                                }
                            })
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final DisplayItemsDao displayItemsDao;
        private final CompanyDao companyDao;

        private PopulateDbAsync(ItemsDatabase db) {
            displayItemsDao = db.displayItemsDao();
            companyDao = db.companyDao();
        }

        @Override
        protected Void doInBackground(Void... params) {
            companyDao.deleteAllCompanies();
            displayItemsDao.deleteAll();

            companyDao.insert(new Company("BioCare", ImageRetriever.createUrl("biocare_logo")));
            companyDao.insert(new Company("Vogel", ImageRetriever.createUrl("vogel_logo")));
            ItemsDatabase.populateBioCare(displayItemsDao);


            return null;
        }
    }

    private static void populateBioCare(DisplayItemsDao displayItemsDao) {
        displayItemsDao.insert(new DisplayItems(
                "biocare_sawpalmettocomplex",
                "Saw Palmetto Complex 60 Capsules",
                "Saw Palmetto Complex provides a synergistic blend of nutrients including  saw palmetto with lycopene, zinc, selenium, celery seed, vitamin A, vitamin B6, l-glysine, l-alanine and beta sitosterol.",
                "Specific Nutrient Complexes",
                "60", 0, "Plant Phytosterols (Providing Beta Sitosterol), Capsule Shell (HPMC), L-Alanine, L-Glycine, Zinc Gluconate, Modified Tapioca Starch, Saw Palmetto Extract (Serenoa repens Berry), Silicon Dioxide, Vitamin C (as Ascorbic Acid), Anti-Caking Agents (Magnesium Stearate), Soya Oil, Vitamin B6 (as Pyridoxine Hydrochloride), Lycopene, Celery Seed Extract (Apium graveolens Seed), Vitamin A (as Retinyl Palmitate), Sodium Selenite, Antioxidant (Natural Mixed Tocopherols), Sunflower Oil. ",
                "Formerly Prostate Complex \n Saw palmetto may be particularly useful for men's health \n Vitamin B6 contributes to the regulation of hormonal activity \n Zinc contributes to normal fertility, reproduction and the maintenance of normal testosterone levels in the blood \n Vitamin C contributes to the protection of cells from oxidative stress \n Vitamin A has a role in the process of cell specialisation \n For full allergen information, scroll over label above",
                "One capsule taken twice daily with food, or as professionally directed",
                "Long term intake of amounts greater than 10mg of vitamin B6 daily may lead to mild tingling and numbness. Total dietary intake (from foods and supplements) of phytosterols should not exceed 3g/day. This product should not be taken by breastfeeding women or children under 5 years. This product contains vitamin A. Do not take if you are pregnant or if you are likely to become pregnant except on the advice of a doctor or ante-natal clinic. This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle. Do not exceed the stated recommended daily intake. Do not purchase if the seal is broken. Keep out of reach of children. If you are under medical supervision, please consult a doctor before use. Store below 25&deg;C in a dry place away from direct sunlight and heat",
                ImageRetriever.createUrl("biocare_sawpalmettocomplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_maleforteplus",
                "Maleforte® Multi - 60 Capsules",
                "MaleForte® Multi is a high potency multivitamin & mineral complex, suited to the special requirements of men. It contains high ratios of nutrients including B vitamins, vitamins C, D, E, zinc, lycopene, ginseng and grapeseed extract.\n ",
                "Multi-Nutrients",
                "60", 0, "Magnesium Citrate, Vitamin C (as Magnesium Ascorbate), Potassium Citrate, Capsule Shell (HPMC), Korean Ginseng (Panax ginseng Root), Pantothenic Acid (as Calcium Pantothenate), Zinc Citrate, Niacin (as Nicotinamide),Vitamin E (as D-Alpha Tocopheryl Succinate), Modified Tapioca Starch, Soya Oil, Thiamine (as Thiamine Hydrochloride) Vitamin B6 (as Pyridoxine Hydrochloride),Riboflavin, Grape Seed Extract (Vitus vinifera Seed as Vitaflavan®), Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide), Lycopene, Dicalcium Phosphate,Chromium Picolinate, Acacia Gum, Sunflower Oil, Sucrose, Corn Starch, Copper Gluconate, Vitamin A (as Retinyl Palmitate), Microcrystalline Cellulose, Sodium Selenite, Folic Acid, Potassium Iodide, Vitamin B12 (as Hydroxycobalamin), Antioxidants (Natural Mixed Tocopherols), Vitamin D2 (as Ergocalciferol).  ",
                "• Suitable for men of all ages\n" +
                        "• Provides nutrients in their most bio-available form for optimum absorption\n" +
                        "• Hypoallergenic formulation in a capsule for easy swallowing\n" +
                        "• B vitamins, including B3, B6, and B12, vitamin C and magnesium contribute to normal energy-yielding metabolism and normal functioning of the nervous system\n" +
                        "• Pantothenic acid contributes to normal synthesis and metabolism of steroid hormones and some neurotransmitters\n" +
                        "• Zinc contributes to the maintenance of normal testosterone levels in the blood, fertility and reproduction\n" +
                        "• Vitamin A, C and D contribute to the normal function of the immune system\n" +
                        "• Vitamin E contributes to the protection of cells from oxidative stress\n" +
                        "• Lycopene and grapeseed extract provide antioxidant properties\n",
                "One capsule taken twice daily with food, or as professionally directed.",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Long term intake of amounts greater than 10mg of vitamin B6 may lead to mild tingling and numbness\n" +
                        "Keep out of reach of children\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_malefortemulti"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_maleforteplus",
                "Maleforte Plus - 30 Capsules",
                "MaleForte® Plus is a synergistic complex of zinc, Korean ginseng, nettle & amino acids, particularly suited to the special requirements of men.\n\n ",
                "Specific Nutrient Complexes",
                "60", 0, "L-Arginine Hydrochloride, Propionyl-L-Carnitine Hydrochloride, Capsule Shell (HPMC), Korean Ginseng (Panax ginseng Root), Nettle Root Extract (Urtica dioica Root), Corn Maltodextrin,  Zinc Citrate, Bulking Agent (Cellulose), Anti-Caking Agent (Magnesium Stearate).  ",
                "• Suitable for men of all ages\n" +
                        "• Provides nutrients in their most bio-available form for optimum absorption\n" +
                        "• Hypoallergenic formulation in a capsule for easy swallowing\n" +
                        "• Zinc contributes to the maintenance of normal testosterone levels in the blood, fertility and reproduction\n" +
                        "• Ginseng helps maintain cognitive performance and counteracts fatigue\n" +
                        "• Nettle supports the body's vitality\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_maleforteplus"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_intrafresh",
                "IntraFresh 6 Vaginal Pessaries",
                "IntraFresh® (vaginal pessary) is a unique women's formulation containing Lactobacillus acidophilus live probiotic bacteria for female freshness & comfort. Supplied with an applicator for easy application.\n\n\n ",
                "Creams and Hygiene",
                "60", 0, "PEG-12, PEG-150, Lactobacillus, Allium Sativum (Garlic) Clove Powder, Rosa Damascena\n" +
                "(Rose) Flower Oil.",
                "• IntraFresh® is a pessary containing an active strain of L.acidophilus\n" +
                        "• The pessary is formed in a soluble wax to ensure stability and to allow uniform dispersion on the vaginal walls and help moisturise for freshness\n" +
                        "• IntraFresh® provides a delicate natural fragrance of rose absolute\n" +
                        "• IntraFresh® is safe and convenient to use\n",
                "Using the applicator, insert one pessary daily, ideally overnight, or as professionally directed. After use, the applicator should be\n" +
                        "washed with soap and warm water, rinsed and dried thoroughly before re-use.\n" +
                        "IntraFresh® may be stored at room temperature (below 25oC)  for one week, but should be kept away from direct sunlight and heat. Keep refrigerated\n" +
                        "for longer periods.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Do not use if pregnant, or if pregnancy is planned\n" +
                        "This product is not a contraceptive and should not be used with condoms\n" +
                        "Keep out of reach of children\n" +
                        "Discontinue use in cases of irritation\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Keep refrigerated for longer periods\n",
                ImageRetriever.createUrl("biocare_intrafresh"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_megaglacomplex",
                "Mega GLA Complex (Omega-6)",
                "Mega GLA Complex combines high potency emulsified omega-6 fatty acids from borage oil.  Each capsule provides approximately 4 times as much GLA (180mg) as a 500mg capsule of EPO.\n",
                "Fatty Acids",
                "60 & 90 & 180", 0, "Borage Oil (Boraginis oleum), Capsule Shell (Modified Maize Starch, Glycerol, Gelling Agent (Carrageenan), Acidity Regulator (Sodium Carbonate)), Vitamin E (as DL-Alpha-Tocopheryl Acetate). ",
                "• Mega GLA Complex provides omega-6 series essential fatty acids\n" +
                        "• Each capsule provides approximately four times as much GLA as a 500mg capsule of Evening Primrose Oil, delivering approximately 180mg of GLA\n" +
                        "• High potency\n" +
                        "• Contains vitamin E to improve stability, and this nutrient also acts as an antioxidant that contributes to the protection of cells from oxidative stress\n" +
                        "• GLA contributes to the maintenance of the normal structure, elasticity and appearance of the skin\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "Caution is advised with anti-coagulant medication\n" +
                        "Not suitable for individuals suffering from epilepsy\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_megaglacomplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_collagencomplex",
                "Collagen Complex - 60 Capsules",
                "Collagen Complex is a high potency nutrient complex with marine collagen, antioxidants and nucleotides.\n",
                "Specific Nutrient Complexes",
                "60", 0, "Marine Collagen (Fish), Capsule Shell (HPMC), Hyaluronic Acid (as Sodium Hyaluronate), Vitamin C (as Ascorbic Acid), Bulking Agent (Cellulose), Rutin, Hesperidin,Nutri-tide® IM Nucleotide Nutrition (Concentrated extracts of RNA Nucleotides from Saccharomyces cerevisiae),  Rosehip (Rosa canina Hips), Zinc Citrate, Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Maltodextrin, Inositol.  ",
                "• A comprehensive, high potency nutrient complex with marine collagen, antioxidants and nucleotides\n" +
                        "• Rapidly absorbed, superior form of type 1 collagen - identical to the collagen found in the body - with excellent research\n" +
                        "• Collagen, hyaluronic acid and nucleotides are components of skin and joints\n" +
                        "• Vitamin C is important for collagen formation and supports the structure of bones, joints and skin\n" +
                        "• Trusted: designed by nutrition experts at BioCare® - the choice of professionals for over 30 years\n" +
                        "• Rosehips are the whole berry fruits of the rose plant and have traditionally been used as a nutrient\n" +
                        "• Vitamin C contributes to the collagen formation and the normal function of bones, teeth, cartilage, gums, skin and blood vessels\n" +
                        "• Vitamin C contributes to maintain the normal function of the immune system during and after intense physical exercise\n" +
                        "• Vitamin C and zinc are antioxidants that contribute to the protection of cells from oxidative stress\n" +
                        "• 30 days' supply at 2 capsules per day\n",
                "One capsule taken twice daily with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Keep out of reach of children\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_collagencomplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_mycopryl",
                "Mycopryl® 400 (Caprylic Acid Complex) 100 Caps",
                "Mycopryl®  400 provides a medium strength caprylic acid, a short chain fatty acid found in human breast milk and coconuts, buffered with calcium and magnesium.\n",
                "Gastrointestinals",
                "100", 0, "Caprylic Acid, Capsule Shell (HPMC), Calcium Hydroxide, Magnesium Hydroxide, Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Bulking Agent (Cellulose).    ",
                "• Mycopryl® is buffered with calcium and magnesium\n" +
                        "• Mycopryl® is not water-soluble like some caprylic acid products such as potassium and zinc caprylate\n",
                "One capsule taken three times daily with food or as professionally directed.\n",
                "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_mycopryl"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_butyricacidcomplex",
                "Butyric Acid 90 Capsules",
                "Butyric acid is a high potency butyrate formula buffered with calcium and magnesium. Butyric acid is a short chain fatty acid produced as the result of the bacterial breakdown of dietary fibre. \n\n",
                "Gastrointestinals",
                "90", 0, "Butyric Acid (as Calcium & Magnesium Butyrate, Capsule Shell  (Hydroxypropyl Methylcellulose), Medium Chain Triglycerides.",
                "• Calcium contributes to the normal function of digestive enzymes\n" +
                        "• Provides a high potency butyrate formula buffered with calcium and magnesium\n",
                "One capsule taken three times a day with food, or as professionally directed.\n" +
                        "NB: This product has a strong sour smell which is completely normal.\n",
                "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_butyricacidcomplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_slipperyelmplus",
                "Slippery Elm Plus 90 Capsules",
                "A combination of slippery elm powder, marshmallow root and gamma oryzanol.\n \n\n",
                "Gastrointestinals",
                "90", 0, "Slippery Elm (Ulmus fulva Bark), Marshmallow (Althaea officinalis Root), Capsule Shell  (HPMC), Gamma Oryzanol (Oryza sativa Bran), Anti-Caking Agent (Magnesium Stearate), Bulking Agent (Cellulose) ",
                "• Combination of slippery elm, marshmallow and gamma oryzanol\n" +
                        "• Capsules can be opened, ideal for those who have difficulty or prefer not to swallow capsules or tablets\n" +
                        "• Marshmallow may support gastrointestinal health\n" +
                        "• Slippery elm soothes the digestive tract and is a source of mucilage which supports the mucous membranes\n",
                "One capsule taken three times daily 20 minutes before food, or as professionally directed\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "Store below 25oC in a dry place away from direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_slipperyelmplus"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_magnesiumpowder",
                "Magnesium Powder 90g",
                "Magnesium Powder is a complex of magnesium citrate and magnesium malate, suitable for everyday use. It is presented in a pleasant tasting, easily absorbed powder, offering flexibility and convenience.\n",
                "Minerals",
                "90", 0, "Magnesium Citrate, Magnesium Malate.  ",
                "• Magnesium contributes to a number of important functions including normal muscle function and maintenance of normal bones and teeth\n" +
                        "• Magnesium contributes to normal energy-yielding metabolism and the reduction of tiredness and fatigue\n" +
                        "• Magnesium contributes to electrolyte balance\n" +
                        "• Magnesium contributes to normal functioning of the nervous system\n" +
                        "• Magnesium contributes to normal muscle function\n" +
                        "• Magnesium contributes to normal protein synthesis\n" +
                        "• Magnesium contributes to normal psychological function\n" +
                        "• Magnesium contributes to the maintenance of normal bones and teeth\n" +
                        "• Magnesium has a role in the process of cell division\n" +
                        "• Magnesium Malate may be particularly useful for sportspeople or those who undertake extensive exercise\n",
                "One heaped scoop (approx 1.5g) daily with food or as professionally directed.\n" +
                        "For best results, mix the powder into water or juice and stir straight away.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_magnesiumpowder"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_osteointensive",
                "Osteo Intensive - 28 Sachets",
                "Osteo Intensive is a high-dose combination of magnesium and calcium in their bio-available forms with boron, vitamins D and K2. Vitamins K, D, calcium and magnesium contribute to maintenance of normal bones.\n\n",
                "Specific Nutrient Complexes",
                "28", 0, "Calcium Citrate, Magnesium Citrate, Sweetener (Xylitol), Horsetail Extract 4:1 (Equisetum arvense Aerial parts), Acacia Gum, Freeze-Dried Wild Berry Powder (Chokeberry, Elderberry, Blueberry, Blackcurrant & Apple), Vitamin D3 (preparation as Cholecalciferol (Lichen) with Tapioca Maltodextrin, Corn Starch, Fractionated Coconut Oil, Sucrose, Silicon Dioxide, D-Alpha Tocopherol, Ascorbyl Palmitate), Sucrose, Corn Starch, Sodium Borate, Manganese Citrate, Tricalcium Phosphate, Medium Chain Triglycerides, Vitamin K2 (as Menaquinone-7).   ",
                "• Clinically effective - a comprehensive, synergistic nutrient and plant extract formula to support bone health. Calcium, magnesium, manganese and vitamins D and K are needed for the maintenance of normal bones and teeth\n" +
                        "• High potency - 500mg of calcium, 250mg of magnesium and 1000iu of vegan vitamin D3\n" +
                        "• Easy to take - powder form giving you high dose nutrients in one, easy to mix, great-tasting powder\n" +
                        "• Pure - free of unnecessary ingredients and allergens, dairy free, so vegan-friendly\n" +
                        "• Trusted - BioCare has been the choice of professionals for over 30 years. Our products are formulated by our team of nutritionists and used successfully by clinicians with their clients\n" +
                        "• Vegan vitamin D3 sourced from natrual lichen\n" +
                        "• Magnesium and calcium in the best absorbed citrate forms\n" +
                        "• Vitamin K2 as MK7, for maximum effectiveness, and proper utilisation of calcium\n" +
                        "• 28 days' supply\n",
                "One sachet mixed in water, juice or milk and taken daily with food or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "If you are under medical supervision, please consult a doctor before use.\n" +
                        "Not suitable for use during planned pregnancy, pregnancy, or breastfeeding.\n" +
                        "Excessive consumption may produce laxative effects.\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_osteointensive"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_prolactazymeplus",
                "Prolactazyme Plus® (Dairy Enzyme Complex)",
                "Prolactazyme Plus® is a potent combination of the enzymes lactase, bromelain, papain and lipase with Lactobacillus acidophilus and salivarius live probiotic bacteria.\n\n\n",
                "Digestive Enzymes",
                "30 & 90", 0, "Potato Malodextrin, Bulking Agent (Cellulose), Capsule Shell (HPMC), Lactase, Anti-Caking Agents (Silicon Dioxide & Magnesium Sterate), Bromelain, Papain (Sulphites), Lipase, Vegetable Rennin, Lactobacillus acidophilus, Lactobacillus salivarius, Amylase, Sodium Chloride, Sodium Benzoate, Protein Hydrolysate.      ",
                "• All enzymes are from vegetable sources so suitable for vegetarians and vegans\n" +
                        "• Prolactazyme Plus® contains high strength, biologically active enzymes\n" +
                        "• Acid stable enzymes able to retain activity over a range of pH variances\n" +
                        "• Vacuum packed to maintain potency and stability\n",
                "One capsule taken daily with food containing dairy products, or as professionally directed.\n",
                "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Vacuum sealed for stability\n",
                ImageRetriever.createUrl("biocare_prolactazymeplus"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_lactaseenzyme",
                "Lactase Enzyme 15ml",
                "A convenient liquid lactase enzyme formula suitable for adults and children.This high strength formula hydrolyses lactose when added to dairy products such as milk, infant formula and expressed breast milk.\n\n\n\n",
                "Digestive Enzymes",
                "15ml", 0, "Lactase Enzyme.",
                "• A stable and biologically active source of this vegetable enzyme\n" +
                        "• Each 15ml bottle is able to convert approximately 78 pints of milk\n" +
                        "• This high potency, stable and biologically active lactase enzyme is suitable for vegetarians and vegans.\n" +
                        "• Our liquid Lactase Enzyme is acid stable against stomach pH\n" +
                        "• Easily added to liquid dairy products such as milk, baby formula and even expressed breast milk\n",
                "Add five drops to one pint (568ml) of milk and refrigerate for 10 hours before use.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Keep out of reach of children\n",
                ImageRetriever.createUrl("biocare_lactaseenzyme"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_childrenstrawberrybioacidophilus",
                "Children's Strawberry BioAcidophilus 60g",
                "This probiotic powder is ideally suited to children from 6 months old, the elderly, or individuals who are convalescing. Free from artificial colours, flavours and additives, it provides 2 billion live probiotic bacteria per half teaspoon.\n",
                "Probiotics",
                "60g", 0, "Fructooligosaccharides (F.O.S.), Potato Maltodextrin, Freeze Dried Strawberry Powder, Lactobacillus acidophilus, Bifidobacterium bifidum & Bifidobacterium lactis, Citric Acid.  ",
                "• Ideal as a regular daily supplement for children from 6 months of age\n" +
                        "• Guaranteed 2 billion potency per daily intake to end of shelf life if stored correctly\n" +
                        "• Contains the unique, extensively tested LAB<sup>4</sup>complex of friendly bacteria - human compatible strains that are bile and acid tolerant with a high adherence ability and a proven history of safe use\n" +
                        "• Hypoallergenic, dairy-free and free from artificial preservatives, colours and flavours\n" +
                        "• Vaccum packed to maintain the stability of these fragile organisms\n",
                "Half a teaspoon (approximately two and a half grams) mixed into liquid or food daily, or as professionally directed.\n" +
                        "Suitable from 6 months of age.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "Vacuum sealed for stability.\n" +
                        "Refrigerate below 4oC and avoid direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_childrenstrawberrybioacidophilus"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_childrensbananabioacidophilus",
                "Children's Banana BioAcidophilus 60g",
                "This probiotic powder is ideally suited to children from 6 months old, the elderly, or individuals who are convalescing. Free from artificial colours, flavours and additives, it provides 2 billion live probiotic bacteria per half teaspoon.\n",
                "Probiotics",
                "60g", 0, "Fructooligosaccharides (F.O.S.), Freeze Dried Banana Powder, Lactobacillus acidophilus, Bifidobacterium bifidum & Bifidobacterium lactis.    ",
                "• Ideal as a regular daily supplement for children from 6 months of age\n" +
                        "• Guaranteed 2 billion potency per daily intake to end of shelf life if stored correctly\n" +
                        "• Contains the unique, extensively tested LAB<sup>4</sup>complex of friendly bacteria - human compatible strains that are bile and acid tolerant with a high adherence ability and a proven history of safe use\n" +
                        "• Hypoallergenic, dairy-free and free from artificial preservatives, colours and flavours\n" +
                        "• Vaccum packed to maintain the stability of these fragile organisms\n",
                "Half a teaspoon (approximately two and a half grams) mixed into liquid or food daily, or as professionally directed.\n" +
                        "Suitable from 6 months of age.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "Vacuum sealed for stability.\n" +
                        "Refrigerate below 4oC and avoid direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_childrensbananabioacidophilus"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_cervagyncream",
                "Cervagyn® Cream 50g",
                "Cervagyn® is a unique topical cream containing Lactobacillus live probiotic bacteria with garlic and rose oil to help maintain normal genital freshness and microflora balance.\n\n",
                "Creams and Hygiene",
                "50g", 0, "PEG-8, PEG-32, Lactobacillus, Allium sativum bulb powder, Rosa damascena flower oil.",
                "• Cervagyn® provides a delicate natural fragrance of rose absolute that also adds a moisturising effect to the applied area\n" +
                        "• Topical cream formulated for intimate skin care & freshness\n" +
                        "• Convenient to use cream. For external use only\n",
                "Apply a thin layer of cream to the external genital area daily, \n" +
                        "ideally overnight, or as professionally directed. Use of sanitary\n" +
                        "protection is advisable.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Do not use if pregnant, or if pregnancy is planned\n" +
                        "This product is not a contraceptive and should not be used with condoms\n" +
                        "Discontinue use in cases of irritation\n" +
                        "Store below 25&deg;C and avoid direct sunlight and heat\n" +
                        "Keep out of reach of children\n",
                ImageRetriever.createUrl("biocare_cervagyncream"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_zinccitrate",
                "Zinc Citrate 180 Tablets",
                "Zinc has many general health benefits including its contribution to normal cognitive function, maintenance of normal hair, skin, nails and bones and normal fertility and reproduction.  The citrate form of zinc has a high absorption rate.\n\n\n",
                "Minerals",
                "180 & 90 ", 0, "Bulking Agents (Dicalcium Phosphate & Microcrystalline Cellulose), Zinc Citrate, Sodium Carboxymethylcellulose, Anti-Caking Agent (Magnesium Stearate). ",
                "• Water soluble tablet that can be taken as a 'lozenge' if required\n" +
                        "• Zinc is a mineral, essential for many vital enzymatic processes\n" +
                        "• Zinc contributes to normal  reproductive and immune system, for tissue renewal, maintenance of the skin and for healthy bones\n" +
                        "• Citrates have a high absorption rate and require little acidification prior to absorption\n" +
                        "• Suitable for people with low stomach acid and poor zinc status\n" +
                        "• Zinc contributes to normal cognitive function\n" +
                        "• Zinc contributes to the maintenance of normal hair, skin, nails and bones\n" +
                        "• Zinc contributes to the normal metabolism of vitamin A\n" +
                        "• Zinc contributes to normal fertility and reproduction\n" +
                        "• Zinc contributes to the maintenance of normal testosterone levels in the blood\n",
                "One tablet taken daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_zinccitrate"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_nutrisorbliquidtraceminerals",
                "Nutrisorb® Liquid Trace Minerals 15ml",
                "A highly absorbable liquid form of trace minerals in a purified water base, ideal for those who cannot swallow capsules or tablets. Provides chromium, selenium, molybdenum, boron and manganese.\n\n\n\n",
                "Minerals",
                "15ml", 0, "Purified Water, Citric Acid, Manganese Sulphate, Chromium Chloride, Sodium Borate, Sodium Molybdate, Anhydrous Sodium Selenate.  ",
                "• Highly bio-available liquid formulation that is ideal for those unable or who prefer not to take capsules or tablets\n" +
                        "• Can be taken under the tongue, added to water, fruit juice or milk\n" +
                        "• May be useful for individuals with poor mineral status\n" +
                        "• Presented as a simple hypoallergenic water base                                                                                                \n" +
                        "• Chromium contributes to normal nutrient metabolism and to the maintenance of normal blood glucose levels\n" +
                        "• Suitable for the elderly\n" +
                        "• Selenium contributes to the normal function of the thyroid and immune system\n" +
                        "• Selenium is an antioxidant that contributes to the protection of cells from oxidative damage\n" +
                        "• Selenium contributes to the maintenance of normal hair and nails\n" +
                        "• Molybdenum contributes to normal sulphur amino acid metabolism\n",
                "Four drops taken daily in water with food, or directly under the tongue or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_nutrisorbliquidtraceminerals"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_phosphatidylserine",
                "Phosphatidyl Serine 30 Capsules",
                "Phosphatidyl Serine is a phospholipid which is presented in a capsule which provides 200mg of phospholipids from lecithin.\n",
                "Amino Acids",
                "30", 0, "Soy Lecithin (Glycine max. L.Merr Bean providing Phosphatidylserine), Capsule Shell (HPMC), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate).",
                "• Provides 200mg of phospholipids from lecithin\n",
                "One capsule taken twice daily 15 minutes before food, or as professionally directed.\n",
                "Not suitable to be taken in conjunction with mood modifying drugs or sleeping pills\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_phosphatidylserine"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_nutrisorbliquidchromium",
                "Nutrisorb® Liquid Chromium 15ml",
                "A highly bioavailable yeast-free, liquid form of chromium providing 220mcg elemental chromium in a purified water base. Chromium contributes to the maintenance of normal blood glucose levels and to macronutrient metabolism.\n\n",
                "Minerals",
                "15ml", 0, "Purified Water, Chromium Chloride, Citric Acid, Preservative (Potassium Sorbate). ",
                "• Liquid form provides a readily absorbable source of chromium, ideal for those who have difficulty or choose not to swallow capsules or tablets\n" +
                        "• Ideal for people with digestive and absorption difficulties\n" +
                        "• Chromium levels may decline with increasing age\n" +
                        "• Chromium contributes to the maintenance of normal blood glucose levels\n" +
                        "• Chromium contributes to normal macronutrient metabolism\n" +
                        "• Suitable for the elderly\n",
                "One drop taken twice daily in water with food, or directly under the tongue, or as professionally directed.\n\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_nutrisorbliquidchromium"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_microCellessentialfattyacids",
                "MicroCell® Essential Fatty Acids 120 Caps",
                "Provides an ideal balance of omega-3 and omega-6 fatty acids from linseed and borage oils micellised by BioCare's unique MicroCell® process ensuring effective absorption and delivery of these fatty acids to the body.\n\n\n",
                "Fatty Acids",
                "120 & 60", 0, "Potato Maltodextrin, Modified Tapioca Starch, Linseed Oil (Linum usitatissimum Seed), Capsule Shell (HPMC & Colours [Copper Chlorophyllin & Titanium Dioxide]), Borage Oil (Borago officinalis Seed), Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide), Lemon Puree (Sulphur Dioxide), Antioxidants (Ascorbic Acid, Ascorbyl Palmitate & Natural Mixed Tocopherols), Sunflower Oil.   ",
                "• MicroCell® Essential Fatty Acids provides a balance of omega-3 and omega-6 fatty acids, ideal for everyday use\n" +
                        "• Our MicroCell® process ensures high absorption allowing maximum delivery of these fatty acids to the body\n" +
                        "• Ideal source of omega-3 and omega-6 fatty acids for vegetarians and vegans and suitable for use during pregnancy\n" +
                        "• Encapsulated in a chlorophyll vegetable capsule to maintain stability\n" +
                        "• For full allergen information, scroll over label above\n",
                "One capsule taken twice daily with food or as professionally directed.\n",
                "Not suitable for individuals suffering from epilepsy\n" +
                        "Caution is advised with anticoagulant medication\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_microCellessentialfattyacids"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_adultmultivitaminsminerals",
                "Adult Multivitamins & Minerals 30 Capsules",
                "Adult Multivitamins & Minerals is a comprehensive one-a-day capsule for adults providing all round nutritional support. It contains bioavailable minerals for enhanced absorption and is iron and copper-free.\n\n\n\n",
                "Fatty Acids",
                "30 & 60 & 90", 0, "Vitamin C (as Magnesium Ascorbate), Capsule Shell (HPMC), Pantothenic Acid (as Calcium Pantothenate), Vitamin E (as D-Alpha Tocopheryl Acetate), Silicon Dioxide, Niacin (as Nicotinamide), Thiamine (as Thiamine Hydrochloride),, Calcium Citrate, Magnesium Citrate, Zinc Citrate, Riboflavin, Vitamin B6 (as Pyridoxal-5-Phosphate), Potassium Citrate, Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Inositol, Dicalcium Phosphate, PABA, Modified Tapioca Starch, Acacia Gum, Manganese Citrate, Sunflower Oil, Chromium Picolinate, Sucrose, Corn Starch, Folic Acid, Sodium Molybdate, Vitamin A (as Retinyl Palmitate), L-Selenomethionine, Potassium Iodide, Biotin, Vitamin B12 (as Hydroxycobalamin), Antioxidant (Natural Mixed Tocopherols), Vitamin D2 (as Ergocalciferol).    ",
                "• Optimal bioavailability\n" +
                        "• Formulated with people with intolerances and allergies in mind\n" +
                        "• Vitamin C presented in a low acidity form as magnesium ascorbate\n" +
                        "• Contains Vitamin D2 - suitable for vegans\n" +
                        "• Contains yeast-free selenium and natural vitamin E\n" +
                        "• Iron and copper free\n" +
                        "• Vitamin D contributes to the maintenance of normal bones, muscles and teeth\n" +
                        "• Vitamin C, B6, B12, D, selenium and zinc help to contribute to the normal function of the immune system\n" +
                        "• B vitamins contribute to normal energy-yielding metabolism\n" +
                        "• Approved by The Vegetarian Society\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "Long term intake of amounts greater than 10mg vitamin B6 daily may lead to mild tingling and numbness\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_adultmultivitaminsminerals"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitaminb12",
                "Vitamin B12 Veg Capsules",
                "Vitamin B12 contributes to normal energy yielding metabolism, homocysteine metabolism and reduction of tiredness and fatigue.\n\n\n\n\n",
                "Vitamins",
                "30", 0, "Bulking Agents (Cellulose), Capsule Shell (HPMC), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Vitamin B12 (as Hydroxycobalamin).     ",
                "• Vitamin B12 in hydroxycobalamin and cynocobalmin forms\n" +
                        "• Vitamin B12 contributes to normal energy-yielding metabolism\n" +
                        "• Vitamin B12 contributes to normal functioning of the nervous system\n" +
                        "• Vitamin B12 contributes to normal homocysteine metabolism\n" +
                        "• Vitamin B12 contributes to normal psychological function\n" +
                        "• Vitamin B12 contributes to normal red blood cell formation\n" +
                        "• Vitamin B12 contributes to the normal function of the immune system\n" +
                        "• Vitamin B12 contributes to the reduction of tiredness and fatigue\n" +
                        "• Vitamin B12 has a role in the process of cell division\n" +
                        "• This combination can be relevant for vegetarians and vegans\n",
                "One capsule taken daily with food or as professionally directed\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "Store below 25oC in a dry place away from direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_vitaminb12"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_glutenzymeplus",
                "Glutenzyme Plus® 30 Capsules",
                "A combination of the enzymes cellulase, protease and amylase along with 1 billion Lactobacillus brevis and Lactobacillus acidophilus live probiotic bacteria in a single capsule.\n\n\n\n\n\n",
                "Digestive Enzymes",
                "30", 0, "Bulking Agent (Cellulose), Potato Maltodextrin, Capsule Shell (HPMC), Cellulase, Lactobacillus brevis, Gluten Protease, Lactobacillus acidophilus, Anti-Caking Agent (Magnesium Stearate), Amylase.      ",
                "• Acid stable against stomach pH\n" +
                        "• Able to retain digestive capability over a wide range of pH variances\n" +
                        "• All enzymes are from vegetable sources\n",
                "One capsule taken with any meal containing gluten, or as professionally directed.\n",
                "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_glutenzymeplus"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_bioenzyme",
                "BioEnzyme (formerly Digestaid)",
                "BioEnzyme is a combination of biologically active enzymes which are able to survive the acidic conditions of the stomach. Suitable for vegetarians and vegans.\n\n\n\n\n\n\n",
                "Digestive Enzymes",
                "60 & 90", 0, "Potato Maltodextrin, Bulking Agent (Cellulose), Capsule Shell (HPMC), Papain (Sulphites), Bromelain, Protease, Anti-Caking Agent (Vegetable Magnesium Stearate), Lipase, Amylase.       ",
                "• A combination of biologically active, proteolytic, lipolytic and carbolytic enzymes\n" +
                        "• Acid stable against stomach pH\n" +
                        "• Able to retain digestive capability over a wide range of pH variances\n" +
                        "• All enzymes are from vegetable sources\n",
                "One capsule taken with each main meal (3 times daily), or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_bioenzyme"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_bioenzyme",
                "BioEnzyme (formerly Digestaid)",
                "BioEnzyme is a combination of biologically active enzymes which are able to survive the acidic conditions of the stomach. Suitable for vegetarians and vegans.\n\n\n\n\n\n\n",
                "Digestive Enzymes",
                "60 & 90", 0, "Potato Maltodextrin, Bulking Agent (Cellulose), Capsule Shell (HPMC), Papain (Sulphites), Bromelain, Protease, Anti-Caking Agent (Vegetable Magnesium Stearate), Lipase, Amylase.       ",
                "• A combination of biologically active, proteolytic, lipolytic and carbolytic enzymes\n" +
                        "• Acid stable against stomach pH\n" +
                        "• Able to retain digestive capability over a wide range of pH variances\n" +
                        "• All enzymes are from vegetable sources\n",
                "One capsule taken with each main meal (3 times daily), or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_bioenzyme"),
                CompanyConstants.BIOCARE
        ));

    }
}

