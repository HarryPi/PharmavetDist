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
                "biocare_microcellessentialfattyacids",
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
                ImageRetriever.createUrl("biocare_microcellessentialfattyacids"),
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
                "biocare_hairandnailcomplex",
                "Hair and Nail Complex 90 Capsules",
                "Hair and Nail Complex is a multivitamin and mineral complex with lysine and horsetail extract.  It includes biotin and zinc which contribute to the maintenance of normal hair and nails and copper which contributes to the normal hair pigmentation.\n\n\n\n\n\n\n\n",
                "Specific Nutrient Complexes",
                "90", 0, "Calcium Gluconate, L-Lysine Hydrochloride, Capsule Shell (HPMC), Pantothenic Acid (as Calcium Pantothenate), Horsetail Extract 4:1 (Equisetum arvense), Vitamin E (as D-Alpha Tocopherol Acetate), Silicon Dioxide, Iron Citrate, Zinc Citrate, Anti-Caking Agent (Magnesium Stearate), Bulking Agent (Cellulose), Acacia Gum, Vitamin B6 (as Pyridoxine Hydrochloride), Modified Tapioca Starch, Biotin, Copper Gluconate, Sunflower Oil, Vitamin B12 (as Hydroxycobalamin), Sucrose, Corn Starch, Folic Acid, Vitamin A (as Retinyl Palmitate), Antioxidant (Natural Mixed Tocopherols), Vitamin D (as Ergocalciferol). ",
                "• Copper is required for hair pigmentation\n" +
                        "• Zinc is required for the metabolism of protein\n" +
                        "• Vitamin E is an antioxidant that contributes to the protection of cells from oxidative stress\n" +
                        "• Vitamin B12 and iron are essential for the production of red blood cells \n" +
                        "• Biotin contributes to the maintenance of normal hair & skin\n" +
                        "• Copper contributes to the maintenance of normal connective tissues\n" +
                        "• Copper contributes to normal hair & skin pigmentation\n" +
                        "• Copper is an antioxidant and contributes to the protection of cells from oxidative stress\n" +
                        "• Folic acid contributes to normal amino acid synthesis\n" +
                        "• Zinc contributes to the maintenance of normal hair, nails & skin\n" +
                        "• Vitamin B6 contributes to the regulation of hormonal activity and to normal cysteine synthesis\n" +
                        "• Iron contributes to normal formation of red blood cells and haemoglobin\n",
                "One capsule taken three times daily with food or as professionally directed.\n",
                "This product contains iron, which, if taken in excess, may be harmful to very young children. Keep out of sight and reach\n" +
                        "Amounts greater than 20mg of iron daily may cause mild stomach upset in sensitive individuals\n" +
                        "This product contains vitamin A. Do not take if you are pregnant or if you are likely to become pregnant except on the advice of a doctor or ante-natal clinic\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_hairandnailcomplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_antenatalforte",
                "AnteNatal Forte (Pregnancy Multinutrient) 60 Caps",
                "AnteNatal Forte provides comprehensive nutritional support for use before and during pregnancy, including 400mcg of folic acid. The carefully chosen nutrients are presented in their most biologically effective forms to assist absorption.\n\n\n\n\n\n\n\n\n",
                "Specific Nutrient Complexes",
                "60", 0, "Vitamin C (as Magnesium Ascorbate), Magnesium Citrate, Calcium Citrate, Vitamin E (as D-Alpha Tocopheryl Succinate), Capsule Shell (HPMC), Choline Bitartrate, Corn Starch, Iron Citrate, Zinc Citrate, Vitamin B6 (as Pyridoxine Hydrochloride), Dicalcium Phosphate Anti-caking Agents (Magnesium Stearate &  Silicon Dioxide), Olive Oil, Bilberry (Vaccinium myrtillus Berry), Beta Carotene, Inositol, Potato Maltodextrin,  Acacia Gum, Sunflower Oil, Chromium Picolinate, Sucrose, Microcrystalline Cellulose, Antioxidants (Ascorbic Acid, Ascorbyl Palmitate & Natural Mixed Tocopherols), Folic Acid, Potassium Iodide, Citric Acid , L-Selenomethionine, Vitamin B12 (as Hydroxycobalamin), Vitamin D2 (as Ergocalciferol).  ",
                "• Improved formulation - increased levels of vitamin D, magnesium, and iron with the addition of choline and iodine. These improvements have been made in line with the latest research\n" +
                        "• A combination of nutrients balanced to support the physiological requirements of pregnancy\n" +
                        "• Can be used prior to, during and after pregnancy\n" +
                        "• Provides nutrients in their most biologically active forms to assist absorption\n" +
                        "• The Department of Health recommends that women who could become pregnant or who are already pregnant take Folic Acid daily (400 micrograms) before conception and throughout the first 12 weeks of pregnancy\n" +
                        "• Provides vitamin C in a buffered, low-acid form to reduce stomach irritation\n" +
                        "• Zinc contributes to normal fertility and reproduction\n" +
                        "• Vitamin B6, B12, D, zinc, iron, selenium and folate contribute to the normal function of the immune system\n" +
                        "• Vitamin B6 contributes to the regulation of hormonal activity\n" +
                        "• Vitamins B12, C, iron, magnesium and folate, contribute to the reduction of tiredness and fatigue\n" +
                        "• Vitamin B12 has a role in the process of cell division\n" +
                        "• Folic acid contributes to maternal tissue growth during pregnancy\n",
                "One capsule taken twice daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Long term intake of amounts greater than 10mg of vitamin B6 may lead to mild tingling and numbness\n" +
                        "This product contains iron, which, if taken in excess may be harmful to very young children\n" +
                        "Keep out of sight and reach\n",
                ImageRetriever.createUrl("biocare_antenatalforte"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_acidophilus_powder",
                "Acidophilus Powder 60g",
                "In powder form containing 4 billion viable organisms of Lactobacillus acidophilus per gram (1/4 teaspoon) in a base of fructooligosaccharides (F.O.S.)\n",
                "Probiotics",
                "60g", 0, "Fructooligosaccharides (F.O.S.), Lactobacillus acidophilus ",
                "• Provides a guaranteed 8 billion potency human strain, acid resistant cells per daily intake to the end of shelf life if stored correctly\n" +
                        "• Dairy-free\n" +
                        "• Fructooligosaccharides (F.O.S.) is derived from chicory root and is a naturally occurring fibre \n" +
                        "• Easily added to food or drink for those people with swallowing difficulties or a preference for powder over capsules or tablets\n" +
                        "• Vacuum packed to maintain the stability of this fragile organism\n",
                "Two grams taken daily (approximately half a teaspoon) mixed in liquid or food, or as professionally directed\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Vacuum sealed for stability\n" +
                        "Refrigerate below 4&deg;C and avoid direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_acidophilus_powder"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_bioacidophilusforte",
                "BioAcidophilus Forte",
                "Our handy 7 day pack of BioAcidophilus Forte which provides 24 billion live probiotic bacteria including the clinically researched LAB4 blend.\n",
                "Probiotics",
                "7 & 30 & 60", 0, "Lactobacillus acidophilus, Capsule (HPMC), Fructooligosaccharides (Cichorium intybus Root),  Bulking Agent (Cellulose),  Anti-caking Agents (Silicon Dioxide & Magnesium Stearate), Bifidobacterium bifidum & Bifidobacterium lactis .  ",
                "• Guaranteed 24 billion potency of the unique, extensively tested LAB4 complex of friendly bacteria per daily intake to end of shelf life if stored correctly\n• Human compatible strains that are bile and acid tolerant with high adherence ability and a proven history of safe use in humans\n• Hypoallergenic and dairy-free\n• Contains Fructooligosaccharides (FOS) derived from chicory \n",
                "One capsule taken daily with food at least 2 hours apart from any medication or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_bioacidophilusforte"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_mycopryl250",
                "Mycopryl® 250 (Caprylic Acid Complex) 60 Caps",
                "Mycopryl®  250 provides a low strength caprylic acid, a short-chain fatty acid found in human breast milk and coconuts, buffered with calcium and magnesium.  Suitable for the elderly.\n",
                "Gastrointestinals",
                "60", 0, "Caprylic Acid, Capsule Shell (HPMC), Magnesium Hydroxide, Calcium Hydroxide, Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate)   ",
                "• Mycopryl® is buffered with calcium and magnesium\n" +
                        "• Mycopryl® is not water-soluble like some caprylic acid products such as potassium and zinc caprylate\n",
                "One capsule taken twice daily with food, or as professionally directed.\n" +
                        "NB: Mycopryl 250 capsules should not be opened or chewed.\n",
                "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_mycopryl250"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_mycopryl_680",
                "Mycopryl® 680 (Caprylic Acid Complex) 90 Caps",
                "Mycopryl® 680 provides a high strength caprylic acid, a short-chain fatty acid found in human breast milk and coconuts, buffered with calcium and magnesium.\n\n",
                "Gastrointestinals",
                "90", 0, "Caprylic Acid (as Calcium Magnesium Caprylate), Capsule Shell (HPMC), Glycerol Trimyristate.      ",
                "• Mycopryl® is buffered with calcium and magnesium\n" +
                        "• Mycopryl® is not water-soluble like some caprylic acid products such as potassium and zinc caprylate\n",
                "One capsule taken three times daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_mycopryl_680"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_bioacidophilus",
                "BioAcidophilus Capsules",
                "BioAcidophilus is a high potency blend of the LAB4 complex of live probiotic bacteria. Each capsule contains 10 billion viable bacteria, 20 billion per daily intake.\n\n\n",
                "Probiotics",
                "120 & 60", 0, "Bulking Agent (Cellulose), Fructooligosaccharides, Capsule Shell (HPMC), Lactobacillus acidophilus (CUL-60 & CUL-21), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Bifidobacterium bifidum (CUL-20) & Bifidobacterium lactis (CUL-34).       ",
                "• Each capsule contains Lactobacillus acidophilus, Bifidobacterium bifidum & Bifidobacterium lactis\n" +
                        "• Guaranteed 20 billion potency of the unique, extensively tested LAB<sup>4</sup> complex of friendly bacteria per daily intake to end of shelf life if stored correctly\n" +
                        "• BioAcidophilus contains human compatible strains that are bile and acid tolerant with high adherence ability and a proven history of safe use in humans\n" +
                        "• Hypoallergenic and dairy-free\n" +
                        "• Vacuum packed and microencapsulated to maintain the stability of these fragile organisms\n" +
                        "• BioAcidophilus contains Fructooligosaccharides (F.O.S.) derived from chicory\n",
                "One capsule taken twice daily with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Vacuum sealed for stability\n" +
                        "Refrigerate below 4&deg;C and avoid direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_bioacidophilus"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_everydaybioacidophilus",
                "Everyday BioAcidophilus - 28 Capsules",
                "Everyday LAB live bacteria in convenient 7 day strips with no need for refrigeration so can be taken on-the-go\n",
                "Probiotics",
                "28", 0, "Fructooligosaccharides (Cichorium intybus Root), Bulking Agent (Microcrystalline Cellulose), Lactobacillus acidophilus, Capsule Shell (Hydroxypropyl Methylcellulose), Bifidobacterium bifidum & Bifidobacterium lactis, Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate).  ",
                "• Everyday BioAcidophilus contains 10 billion of the clinically-researched LAB4  combination per capsule with two specific proprietary strains of Lactobacillus acidophilus along with Bifidobacterium bifidum and Bifidobacterium lactis\n" +
                        "• Free from common allergens like dairy\n" +
                        "• Contains natural prebiotics to feed the live bacteria\n" +
                        "• Convenient 7 days strips - handy for on-the-go - keep in your bag or on your desk\n",
                "One or two capsules taken daily with food, or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "If you are under medical supervision, please consult a doctor before use.\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_everydaybioacidophilus"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_babyiinfantispowder",
                "Baby Infantis Powder 60g",
                "Baby Infantis Powder contains Bifidobacteria live probiotic bacteria (found in breast milk) and is suitable for children of all ages but most appropriate to infants prior to weaning. A 1/4 teaspoon/1 gram contains four billion live bacteria.\n\n",
                "Probiotics",
                "60g", 0, "Fructooligosaccharides (F.O.S.), Bifidobacterium lactis.   ",
                "• Easily mixed into a paste with sterile water or added to soft food for consumption\n" +
                        "• Provides a single strain of Bifidobacterium lactis\n" +
                        "• Bifidobacteria are the main friendly intestinal bacteria in breast-fed infants\n" +
                        "• Contains Fructooligosaccharides (F.O.S.)\n" +
                        "• Human strain and acid resistant\n" +
                        "• Dairy-free\n" +
                        "• Vacuum packed for stability\n",
                "One gram (one quarter teaspoon) daily mixed into a small amount of sterile water or suitable milk to form a paste.\n" +
                        "Give orally alongside baby’s feed. During weaning, Infantis can be mixed into cool or warm soft food. Suitable from birth and up to around 1 year of age.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Vacuum sealed for stability\n" +
                        "Refrigerate below 4&deg;C and avoid direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_babyiinfantispowder"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_childrensredberrybiomelts",
                "Children's Red Berry BioMelts - 28 Sachets",
                "Individual sachets of unique live bacteria with vitamin D. A delicious, natural red berry flavoured powder that melts on the tongue.\n",
                "Probiotics",
                "28", 0, "Bulking Agents (Erythritol & Xylitol), Glazing Agents (Silicon Dioxide, Mono- and Diglycerides of Fatty Acids), Anti-Caking Agent (Magnesium Oxide), Maltodextrin, Citric Acid, Lactobacillus rhamnosus, Bifidobacterium lactis, Carboxymethylcellulose, Binder (Sodium Carboxymethylcellulose), Dextrin, Natural Flavours (Strawberry & Raspberry), Modified Corn Starch, Sucrose, Acacia Gum, Antioxidants (DL-Alpha Tocopherol & Sodium Ascorbate), Medium Chain Triglycerides, Vitamin D3 (as Cholecalciferol).     ",
                "• An all in one solution of live bacteria with vitamin D for every day and immune support in one hit\n" +
                        "• BioMelts were developed using the clinically researched Lactobacillus rhamnosus GG and Bifidobacterium lactis (3 billion)\n" +
                        "• Great tasting, melt on the tongue powder that children will love to take themselves every day\n" +
                        "• Convenient stick sachet to take with you on-the-go!\n" +
                        "• Contains no artificial flavours or colours and free from common allergens like dairy\n" +
                        "• Naturally sweetened with prebiotic xylitol\n" +
                        "• Provides 7.5mcg vitamin D3 (cholecalciferol)\n" +
                        "• Vitamin D contributes to the normal function of the immune system\n" +
                        "• Vitamin D contributes to the maintenance of normal bones and teeth\n" +
                        "• The Department of Health recommends a daily supplement of 10mcg vitamin D should be given to young children from 6 months to 5 years of age\n" +
                        "• 28 days' supply at one sachet a day\n" +
                        "• Suitable for children from 3 years\n" +
                        "• Vegetarian and dairy-free\n",
                "One sachet taken daily, directly onto the tongue, alongside food or as professionally directed. Suitable from three years of age.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "If you are under medical supervision please consult a doctor before use.\n" +
                        "Excessive consumption may produce laxative effects.\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_childrensredberrybiomelts"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitaminc500",
                "Vitamin C 500 Capsules",
                "Vitamin C 500 provides a bioavailable source of vitamin C and magnesium, together with bilberry extract. Magnesium Ascorbate is buffered which means as a less acid form of vitamin C it is therefore gentler on the stomach.\n",
                "Vitamins",
                "180 & 60", 0, "Vitamin C (as Magnesium Ascorbate), Capsule Shell (HPMC), Potato Maltodextrin, Bilberry Extract (Vaccinium myrtillus Berry), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Citric Acid. ",
                "• Provides a readily absorbable form of vitamin C and magnesium\n" +
                        "• Buffered, low acidity form of vitamin C to reduce stomach irritation\n" +
                        "• Magnesium ascorbate does not leach minerals such as calcium and potassium from the body\n" +
                        "• Bilberries are one of the richest sources of natural flavonoids\n" +
                        "• Suitable for individuals requiring a citrus-free vitamin C supplement\n" +
                        "• Vitamin C contributes to maintain the normal function of the immune system during and after intense physical exercise\n" +
                        "• Vitamin C contributes to normal collagen formation for the normal function of blood vessels, bones, gums, skin teeth & cartilage\n" +
                        "• Vitamin C and magnesium contribute to normal psychological function\n" +
                        "• Vitamin C contributes to the protection of cells from oxidative stress\n" +
                        "• Magnesium contributes to a reduction of tiredness and fatigue\n" +
                        "• Magnesium contributes to normal muscle function\n" +
                        "• Bilberry anthocyanosides can help capillary vessels health and elasticity of veins against harmful effect of free radicals\n",
                "One or two capsules taken daily with food or as professionally directed.\n\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_vitaminc500"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_b_complex",
                "B Complex Capsules",
                "B Complex provides all the key B vitamins as well as l-glycine  and magnesium to support absorption. BioCare's B Complex is vacuum packed for enhanced stability.\n",
                "Vitamins",
                "30 & 60 & 90", 0, "Magnesium Gluconate, Capsule Shell (HPMC), Thiamine (as Thiamine Hydrochloride), Vitamin B6 (as Pyridoxal-5-Phosphate), Pantothenic Acid (as Calcium Pantothenate), Riboflavin, Niacin (as Nicotinamide), Vitamin C (as Magnesium Ascorbate), Choline Bitartrate, Inositol, L-Glycine,, Para Amino Benzoic Acid, Anti-Caking Agent (Silicon Dioxide & Magnesium Stearate), Folic Acid, Biotin, Vitamin B12 (as Hydroxycobalamin). ",
                "• B Vitamins have many uses including contributing to the release of energy from foods, supporting the healthy function of the nervous system, contributing to the reduction of tiredness and fatigue and supporting normal psychological function\n" +
                        "• Vitamins B6 and B12 are presented in their biologically active forms\n" +
                        "• Vacuum packed to reduce oxidation of the B vitamins\n" +
                        "• Vitamins B6, B2, B12, C and biotin contribute to normal functioning of the nervous system\n" +
                        "• Vitamin B1, B3, B6 and B12 contribute to normal psychological function\n" +
                        "• Vitamin B1, B2, B3, B5 and B6 contribute to normal energy-yielding metabolism\n" +
                        "• Vitamin B2, B3, B5, B6 and B12 contributes to the reduction of tiredness and fatigue\n" +
                        "• Vitamin B6, B12 and folic acid contribute to the normal function of the immune system                                                                                                                     \n",
                "One capsule taken daily with food or as professionally directed.\n",
                "Long term intake of amounts greater than 10mg vitamin B6 daily may lead to mild tingling and numbness\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Vacuum sealed for stability\n",
                ImageRetriever.createUrl("biocare_b_complex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_biomagnesium",
                "BioMagnesium 60 Caps",
                "BioMagnesium provides a bioavailable source of magnesium as magnesium chloride with fumaric acid.  Magnesium is useful in reducing tiredness and fatigue and contributes to energy metabolism and the normal functioning of the nervous system.\n\n",
                "Minerals",
                "60", 0, "Fumaric Acid, Magnesium Chloride, Capsule shell (Hydroxypropyl Methylcellulose), Magnesium Carbonate, Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate).  ",
                "• Magnesium contributes to normal nerve and muscle  function\n" +
                        "• Magnesium is important in maintenance of normal bones and teeth\n" +
                        "• Magnesium contributes to normal energy metabolism\n" +
                        "• Magnesium supports the absorption and retention of calcium\n" +
                        "• Magnesium contributes to electrolyte balance\n" +
                        "• Magnesium is a co-factor in many enzyme systems and hormones\n" +
                        "• Fumaric Acid is a water-soluble, organic acid and may help to facilitate the absorption of minerals\n" +
                        "• A bioavailable form of magnesium\n" +
                        "• Requires virtually no acidification prior to absorption\n",
                "One capsule taken twice daily with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_biomagnesium"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_nutrisorbliquidselenium",
                "Nutrisorb® Liquid Selenium 15ml",
                "Nutrisorb® Liquid Selenium is a highly bioavailable liquid ideal for individuals with absorption problems. Selenium is an antioxidant that contributes to  the protection of cells from oxidative stress.\n\n\n",
                "Minerals",
                "15ml", 0, "Purfied Water, Preservative (Citric Acid), Sodium Selenite.   ",
                "• Provides a highly absorbable and hypoallergenic source of selenium\n" +
                        "• A simple hypoallergenic water base liquid  ideal for those who are unable or prefer not to swallow capsules or tablets\n" +
                        "• Selenium contributes to normal spermatogenesis\n" +
                        "• Selenium contributes to the health of nails and hair\n" +
                        "• Selenium is a component of the antioxidant enzyme, glutathione peroxidase and contributes to the protection of cells from oxidative damage\n" +
                        "• Suitable for the elderly\n" +
                        "• Selenium contributes to the normal function of the immune system\n" +
                        "• Selenium contributes to the normal thyroid function \n",
                "One drop taken twice daily in water with food, or directly under the tongue, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_nutrisorbliquidselenium"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_nutrisorbliquidmanganese",
                "Nutrisorb® Liquid Manganese 15ml",
                "Nutrisorb®  Liquid Manganese is a highly bioavailable liquid formulation, ideal for individuals with absorption problems. Manganese is an essential trace mineral which as an antioxidant contributes to the protection of cells from oxidative damage.\n",
                "Minerals",
                "15ml", 0, "Purified Water, Manganese Ascorbate. ",
                "• Presented as liquid form manganese ascorbate for optimal absorption and bioavailability\n" +
                        "• Ideal for those who have difficulty or prefer not to swallow capsules or tablets\n" +
                        "• Manganese is an antioxidant that contributes to the protection of cells from oxidative damage\n" +
                        "• Manganese contributes to the normal formation of connective tissue\n" +
                        "• Manganese contributes to normal energy production\n" +
                        "• Suitable for the elderly\n" +
                        "• Presented in a simple purified water base\n",
                "One drop taken daily in water with food, or directly under the tongue or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_nutrisorbliquidmanganese"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_nutrisorbliquidiron",
                "Nutrisorb® Liquid Iron 15ml",
                "Nutrisorb® Liquid iron provides iron in a simple, hypoallergenic purified water base.\n",
                "Minerals",
                "15ml", 0, "Purified Water, Ferric Ammonium Citrate, Citric Acid, Preservative (Potassium Sorbate). ",
                "• Nutrisorb® liquids are a\n" +
                        "range of high potency\n" +
                        "liquid nutrients\n" +
                        "• Particularly suited to those individuals who are sensitive to inorganic iron supplementation\n" +
                        "• Iron contributes to normal oxygen transport in the body\n" +
                        "• Iron contributes to the reduction of tiredness and fatigue\n" +
                        "• Iron contributes to normal cognitive function\n" +
                        "• Iron contributes to normal formation of red blood cells and haemoglobin\n",
                "15 drops taken daily in water with food, or directly under the tongue or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake. Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "This product contains iron, which, if taken in excess, may be harmful to very young children. Keep out of reach of children\n",
                ImageRetriever.createUrl("biocare_nutrisorbliquidiron"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_selenium",
                "Selenium Capsules",
                "Provides L-selenomethionine,  a bioavailable and well absorbed combination of selenium and l-methionine. Selenium is an antioxidant that contributes to the protection of cells from oxidative stress and normal thyroid function.\n",
                "Minerals",
                "60 & 120", 0, "Bulking Agents (Dicalcium Phosphate & Cellulose), Capsule Shell  (HPMC), Anti-Caking Agents (Silicon Dioxide &  Magnesium Stearate), L-Selenomethionine  ",
                "• L-selenomethionine is a highly absorbable source of yeast-free selenium\n" +
                        "• Selenium contributes to the maintenance of normal hair and nails\n" +
                        "• Selenium contributes to normal spermatogenesis\n" +
                        "• Selenium contributes to the normal function of the immune system\n" +
                        "• Selenium contributes to the normal thyroid function\n" +
                        "• Selenium is an antioxidant that contributes to the protection of cells from oxidative stress\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_selenium"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_magnesiumtaurate",
                "Magnesium Taurate 60 Capsules",
                "Magnesium Taurate provides magnesium and the amino acid L-taurine.  Magnesium contributes to a reduction in tiredness and fatigue, normal energy-yielding metabolism, the normal function of the nervous system and normal muscle function.\n\n",
                "Minerals",
                "60", 0, "Magnesium Taurate, Capsule Shell (HPMC), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate). ",
                "• Magnesium contributes to a reduction of tiredness and fatigue\n" +
                        "• Magnesium contributes to electrolyte balance\n" +
                        "• Magnesium contributes to normal energy-yielding metabolism\n" +
                        "• Magnesium contributes to normal functioning of the nervous system\n" +
                        "• Magnesium contributes to normal muscle function\n" +
                        "• Magnesium contributes to normal protein synthesis\n" +
                        "• Magnesium contributes to normal psychological function\n" +
                        "• Magnesium contributes to the maintenance of normal bones and teeth\n" +
                        "• Magnesium has a role in the process of cell division\n",
                "One capsule taken twice daily with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_magnesiumtaurate"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_nt188",
                "NT 188 60 Capsules",
                "A specially formulated combination of amino acids such as glutamic acid, chamomile and B vitamins. Vitamins B1, B2 and B3 contribute to the normal functioning of the nervous system and psychological function.\n\n\n",
                "Specific Nutrient Complexes",
                "60", 0, "L-Glutamic Acid, Magnesium Gluconate, Capsule Shell (HPMC), L-Aspartic Acid, Chamomile (Matricaria chamomilla Flower), Calcium Gluconate, Thiamine Hydrochloride, L-Lysine Hydrochloride, Riboflavin, Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Niacin (as Nicotinamide), Choline Bitartrate. ",
                "• Vitamins B1 and B3 contribute to the normal functioning of the nervous system and to normal psychological function\n" +
                        "• Niacin (vitamin B3) contributes to the reduction of tiredness and fatigue\n" +
                        "• Chamomile helps to support relaxation and helps to maintain healthy sleep\n",
                "One capsule taken twice daily with food, or as professionally directed.\n",
                "Not to be used in conjunction with mood modifying drugs\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_nt188"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_calcium_citrate_90",
                "Calcium Citrate - 90 Capsules",
                "Calcium Citrate is dairy-free source of highly bioavailable calcium which is needed for the formation and maintenance of healthy bones and teeth, for normal blood clotting and the normal function of muscles.\n",
                "Minerals",
                "90", 0, "Calcium Citrate, Capsule (HPMC), Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide),  Bulking Agent (Microcrystalline Cellulose).",
                "• Calcium is needed for the formation and maintenance of bones and teeth\n" +
                        "• Calcium contributes to the normal function of digestive enzymes\n" +
                        "• Calcium is necessary for the normal function of muscles and neurotransmission\n" +
                        "• Calcium contributes to normal blood clotting\n" +
                        "• Suitable for individuals with malabsorption and poor calcium status\n",
                "One capsule taken 3 times daily, with food, or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake. Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_calcium_citrate_90"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_cholesterase",
                "Cholesterase 60 Capsules",
                "Cholesterase is a combination of nutrients including vitamin B3,  bromelain, plant sterols and chromium.  Plant sterols contribute to the maintenance of normal blood cholesterol levels with a daily intake of at least 800mg plant sterols.\n",
                "Specific Nutrient Complexes",
                "60", 0, "Potassium Gluconate, Capsule Shell (HPMC), Vitamin C (as Ascorbic Acid), Plant Phytosterols (Providing Beta Sitosterol), Niacin (as Nicotinamide), Vitamin E (as D-Alpha Tocopheryl Acetate), Silicon Dioxide, Potato Maltodextrin, Vitamin B6 (as Pyridoxine Hydrochloride), Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide), Bromelain, Apple Pectin (Malus spp Fruit), L-Cysteine Hydrochloride, Acacia Gum, Chromium Picolinate. ",
                "• Vitamin C & E contribute to the protection of cells from oxidative stress\n" +
                        "• Chromium contributes to normal macronutrient metabolism\n" +
                        "• Chromium contributes to the maintenance of normal blood glucose levels\n" +
                        "• Vitamin B3, B6 and vitamin C contribute to normal energy-yielding metabolism\n" +
                        "• This product provides 150mg of plant sterols, which contribute to the maintenance of normal blood cholesterol levels with a daily intake of at least 800mg plant sterols\n" +
                        "• Vitamin B6 contributes to normal homocysteine metabolism\n",
                "One capsule taken twice daily with food, or as professionally directed.\n",
                "If you are taking cholesterol lowering medication, seek medical advice before use.\n" +
                        "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine.\n" +
                        "Total dietary intake (from foods and supplements) of phytosterols should not exceed 3g/day.\n" +
                        "Not to be taken by pregnant and breastfeeding women or children under 5 years of age.\n" +
                        "Long term intake of amounts greater than 10mg vitamin B6 daily may lead to mild tingling and numbness.\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "If you are under medical supervision, please consult a doctor before use.\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_cholesterase"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_hep194",
                "HEP 194 60 Capsules",
                "HEP 194 is a combination of methionine, beta sitosterol and apple extract with vitamin B2, biotin, choline and inositol.  Biotin contributes to normal macronutrient metabolism, energy metabolism and psychological function.\n",
                "Specific Nutrient Complexes",
                "60", 0, "Choline Bitartrate, Plant Phytosterols (Soya), Inositol, Capsule Shell  (Hydroxypropyl Methylcellulose), L-Methionine, Bulking Agent (Cellulose), Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide), Apple Extract (Malus spp Fruit), Riboflavin, Biotin. ",
                "• Riboflavin & biotin contribute to normal energy-yielding metabolism\n" +
                        "• Riboflavin & biotin contribute to normal functioning of the nervous system\n" +
                        "• Riboflavin & biotin contribute to the maintenance of normal mucous membranes\n" +
                        "• Riboflavin contributes to the maintenance of normal red blood cells\n" +
                        "• Riboflavin contributes to the protection of cells from oxidative stress\n" +
                        "• Riboflavin contributes to the reduction of tiredness and fatigue\n" +
                        "• Biotin contributes to normal macronutrient metabolism\n" +
                        "• Biotin contributes to normal psychological function\n" +
                        "• Biotin contributes to the maintenance of normal hair & skin\n",
                "One capsule taken twice daily with food, or as professionally directed.\n",
                "If you are taking cholesterol-lowering medication seek medical advice before taking this product\n" +
                        "Not to be taken by pregnant and breastfeeding women or children under 5 years\n" +
                        "Total dietary intake (from foods and supplements) of phytosterols should not exceed 3g/day\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Not suitable for children under 5 years\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_hep194"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_magnesiumcitrate",
                "Magnesium Citrate - 90 Capsules",
                "Magnesium Citrate is a potent supplement of magnesium that is easily absorbed and used by the body. It provides 100mg of elemental magnesium per capsule and is suitable for everyday use.\n",
                "Minerals",
                "90", 0, "Magnesium Citrate, Capsule Shell (HPMC), Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide).  ",
                "• Magnesium contributes to normal energy-yielding metabolism and the reduction of tiredness and fatigue\n" +
                        "• Magnesium contributes to electrolyte balance\n" +
                        "• Magnesium contributes to normal functioning of the nervous system\n" +
                        "• Magnesium contributes to normal muscle function\n" +
                        "• Magnesium contributes to normal protein synthesis\n" +
                        "• Magnesium contributes to normal psychological function\n" +
                        "• Magnesium contributes to the maintenance of normal bones and teeth\n" +
                        "• Magnesium has a role in the process of cell division\n" +
                        "• Magnesium Citrate is easily absorbed and used by the body.\n",
                "One capsule taken 3 times daily with food, or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake. Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_magnesiumcitrate"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_magnesium_phospholipid_comp",
                "Magnesium Phospholipid Complex",
                "Magnesium Phospholipid Complex is a unique combination that supports the nervous and psychological systems by combining magnesium with phosphatidylserine from sunflower.\n",
                "Minerals",
                "90", 0, "Magneisum Citrate, Magnesium Bisglycinate, Capsule Shell (HPMC), Sunflower Lecithin (as Sharp-PS Green TM), Anti-Caking Agents (Magneisum Stearate & Silicon Dioxide), Tapioca Maltodextrin, Sunflower Lipids, Antioxidants (Ascorbyl Palmitate & Natural Mixed Tocopherols).  ",
                "• Magnesium contibutes to a number of important functions including normal muscle function and maintenance of normal bones and teeth as well as contributing to normal energy metabolism\n" +
                        "• Magnesium contributes to electrolyte balance\n" +
                        "• Magnesium contributes to normal functioning of the immune system\n" +
                        "• Magnesium contributes to normal protein synthesis\n",
                "One capsule taken three times daily with food, or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Store below 25&deg; in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_magnesium_phospholipid_comp"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_neuro_complex",
                "Neuro Complex 60 Capsules",
                "Neuro Complex is a unique, high potency combination of sage, rosemary, saffron, alpha lipoic acid, carnitine, phosphatidylserine, with zinc and vitamin B5 to support nerve health and cognition.\n",
                "Specific Nutrient Complexes",
                "60", 0, "Choline Bitartrate, Capsule Shell (HPMC), N-Acetyl L-Carnitine Hydrochloride, Microcrystalline Cellulose, Zinc Ascorbate, Sunflower Lecithin (as Sharp-PS GreenTM), Pantothenic Acid (as Calcium Pantothenate), Alpha Lipoic Acid, Rosemary Extract (Rosmarinus officinalis Leaf), Sage Extract (Salvia officinalis Leaf), Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide), Saffron Powder (Crocus sativus Stigma), Sunflower Lipids, Tapioca Maltodextrin, Antioxidants (Ascorbyl Palmitate & Natural Mixed Tocopherols). ",
                "• Zinc and vitamin B5 support nervous health and cognitive function\n" +
                        "• Sharp-PS®  extract is sourced from sunflower lecithin, rather than the more commonly used, soya derivative, offering a new allergen and GMO free alternative\n" +
                        "• In a capsule form for simple, convenient dosing\n" +
                        "• 30 days' supply\n",
                "One capsule taken twice daily with food, or as professionally directed\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Not suitable for use during pregnancy, planned pregnancy, or breastfeeding\n" +
                        "Not suitable for individuals taking sleeping or mood modifying medication\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_neuro_complex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_mineralcomplex",
                "Mineral Complex Capsules",
                "Provides magnesium, zinc, manganese, copper, chromium, molybdenum and selenium in a convenient capsule form.These minerals can help to support bone health, energy production, red blood cell formation, immunity and other metabolic processes.\n",
                "Minerals",
                "30 & 90", 0, "Magnesium Citrate, Bulking Agent (Microcrystalline Cellulose), Capsule Shell (HPMC), Zinc Citrate, Manganese Gluconate, Dicalcium Phosphate, Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide), Sodium Borate, Copper Gluconate, Chromium Picolinate, Sodium Molybdate, L-Selenomethionine. ",
                "• Mineral Complex may be useful for individuals with poor mineral status\n" +
                        "• Magnesium contributes to normal functioning of the nervous system, muscles, maintenance of bones and teeth, and has a role in the process of cell division\n" +
                        "• Zinc contributes to the maintenance of hair, skin, nails and bones\n" +
                        "• Zinc contributes to the normal function of the immune system and  as an antioxidant to the protection of cells from oxidative stress\n" +
                        "• Manganese contributes to the normal formation of connective tissue and the maintenance of bones\n" +
                        "• Copper, manganese and magnesium contribute to normal energy-yielding metabolism\n" +
                        "• Molybdenum contributes to normal sulphur amino acid metabolism\n" +
                        "• Chromium contributes to normal nutrient metabolism and to the maintenance of normal blood glucose levels\n" +
                        "• Selenium contributes to the normal function of the immune system and as an antioxidant to the protection of cells from oxidative stress\n",
                "One capsule taken daily with food or as professionally directed.\n" +
                        "Do not open capsule.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_mineralcomplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_ad206",
                "AD 206 60 Capsules",
                "AD206 contains vitamin B6, niacin & pantothenic acid with Siberian ginseng, licorice root and chromium. Vitamins B3, B5, B6 and C contribute to normal energy metabolism and the reduction of tiredness and fatigue.\n\n",
                "Specific Nutrient Complexes",
                "60", 0, "Pantothenic Acid (as Calcium Pantothenate), Vitamin C (as Ascorbic Acid), Siberian Ginseng (Eleutherococcus senticosus Root), Capsule Shell (HPMC), Licorice (Glycyrrhizia glabra Root), Magnesium Gluconate, Gotu Kola 4:1 Extract (Centella asiatica Arial Parts), Vitamin B6 (as Pyridoxine Hydrochloride), Apple Extract (Pyrus malus Fruit), Niacin (as Nicotinamide), Zinc Citrate, Anti-Caking Agent (Magnesium Stearate), Manganese Gluconate, Chromium Picolinate. ",
                "• Niacin, vitamin C, vitamin B6 and pantothenic acid contribute to normal energy-yielding metabolism and contribute to the reduction of tiredness and fatigue\n" +
                        "• Vitamin B6, niacin contribute to normal functioning of the nervous system\n" +
                        "• Pantothenic acid contributes to normal synthesis and metabolism of steroid hormones, vitamin D and some neurotransmitters\n" +
                        "• Vitamin B6, C contributes to normal psychological function\n" +
                        "• Chromium contributes to the maintenance of normal blood glucose levels\n" +
                        "• Ginseng may contribute to physical and mental recovery and assist with mental performance \n",
                "One capsule taken twice daily with food, or as professionally directed.\n",
                "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "Long term intake of amounts greater than 10mg vitamin B6 daily may lead to mild tingling and numbness\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "\n",
                ImageRetriever.createUrl("biocare_ad206"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_th207",
                "TH 207  60 Capsules",
                "TH207 is a unique combination of herbs, nutrients and enzymes including kelp, vitamin C, Siberian ginseng, niacin and thiamine. It also provides iodine (from kelp) and selenium which contribute to the normal thyroid function.\n",
                "Specific Nutrient Complexes",
                "60", 0, "Kelp Extract (Ascophyllum nodosum Seaweed), Capsule Shell (HPMC), Vitamin C (as Calcium Ascorbate), Magnesium Gluconate, Bulking Agent (Cellulose), Siberian Ginseng Powder (Eleutherocus senticosus Root), Niacin (as Nicotinamide), Licorice Powder (Glycyrrhizia glabra Root), L-Tyrosine, L-Glycine, Anti-Caking Agent (Magnesium Stearate), Thiamine (as Thiamine Hydrochloride), L-Glutamine, Modified Tapioca Starch , Potato Maltodextrin, Dicalcium Phosphate, Vitamin A (as Retinyl Palmitate), Cellulase, Lipase, Protease, Amylase, L-Selenomethionine, Antioxidant (Natural Mixed Tocopherols), Sunflower Oil.  ",
                "• Iodine and selenium contribute to the normal thyroid function\n" +
                        "• Vitamin C, niacin and magnesium contribute to normal energy-yielding metabolism\n" +
                        "• Licorice root is nutritive and helps maintain energy levels\n" +
                        "• B vitamins are required for the release of energy from food, and for the healthy function of the nervous system\n" +
                        "• Siberian ginseng is an antioxidant that may help with energy supply\n" +
                        "• Suitable for vegetarians and is an excellent alternative to glandular\n",
                "One capsule taken twice daily with food, or as professionally directed.\n",
                "Not suitable for use during pregnancy, planned pregnancy or breastfeeding\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_th207"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_nt_intensive",
                "NT Intensive Sachet",
                "NT Intensive is a unique, high potency powder combination. It contains a synergistic blend of amino acids, lecithin and the unique ingredient Cyracos® (lemon balm extract) in a single, great tasting and convenient sachet.\n",
                "Specific Nutrient Complexes",
                "1 & 14", 0, "Magnesium Taurate, Soy Lecithin (Glycine maxx L. Merr Beans), Natural Orange Flavour, Fructose, Lemon Balm Extract (Melissa officinalis Aerial Parts as Cyracos™), Fructooligosaccharides (Chicorium intybus Root), L- Theanine, L-Tryptophan, Preservative (Citric Acid). ",
                "• Contains lemon balm (Cyracos®), theanine, and taurine\n" +
                        "• Magnesium contributes to normal energy-yielding metabolism and the normal functioning of the nervous system\n" +
                        "• Lecithin is a precursor to phosphatidyl serine\n" +
                        "• L-tryptophan is an amino acid and precursor to serotonin, melatonin and vitamin B3 (which contributes to the reduction of tiredness and fatigue)\n" +
                        "• Designed to suit individual needs - can be used alone or in combination with other products in the range\n" +
                        "• NT Intensive can easily be mixed into liquids\n" +
                        "• For full allergen information, scroll over label above\n",
                "One sachet mixed in water, juice or milk and taken daily with food or as professionally directed.\n",
                "Not suitable for individuals taking sleeping or mood modifying medication.\n" +
                        "Not suitable for use during planned pregnancy, pregnancy, or breastfeeding.\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "If you are under medical supervision, please consult a doctor before use.\n" +
                        "Store below 25oC in a dry place away from direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_nt_intensive"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_mag2_1cal",
                "Mag2:1Cal (Magnesium & Calcium) 90 Caps",
                "Mag 2:1 Cal provides a balanced ratio of magnesium and calcium in well-absorbed succinate form. Calcium  is needed for the maintenance of normal bones and teeth and magnesium contributes to the normal functioning of the muscles and nervous system.\n",
                "Minerals",
                "90", 0, "Magnesium Succinate, Calcium Succinate, Capsule Shell (HPMC) , Anti-Caking Agent (Magnesium Stearate). ",
                "• Provides a balanced ratio of magnesium and calcium in well-absorbed succinate form\n" +
                        "• Magnesium contributes to a reduction of tiredness and fatigue\n" +
                        "• Magnesium contributes to electrolyte balance\n" +
                        "• Magnesium contributes to normal energy-yielding metabolism\n" +
                        "• Magnesium contributes to normal functioning of the nervous system\n" +
                        "• Calcium contributes to normal blood clotting\n" +
                        "• Calcium contributes to normal energy-yielding metabolism\n" +
                        "• Calcium contributes to normal neurotransmission\n" +
                        "• Calcium contributes to the normal function of digestive enzymes\n" +
                        "• Calcium has a role in the process of cell division and specialisation\n" +
                        "• Calcium is needed for the maintenance of normal bones and teeth\n" +
                        "• Calcium and magnesium contribute to normal muscle function\n",
                "One capsule taken three times daily with food or as professionally directed.\n",
                "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_mag2_1cal"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_nutrisorbliquidmolybdenum",
                "Nutrisorb® Liquid Molybdenum 15ml",
                "Nutrisorb® Liquid Molybdenum is ideal for individuals with absorption difficulties. This highly bioavailable liquid provides 200mcg of molybdenum along with vitamin C in a simple purified water base.\n",
                "Minerals",
                "15ml", 0, "Purified Water, Vitamin C (as Ascorbic Acid), Sodium Molybdate. ",
                "• Highly bio-available liquid formulation for better absorption\n" +
                        "• Ideal for those unable or who prefer not to swallow capsules or tablets                                                       \n" +
                        "• Provides a highly absorbable and hypoallergenic source of molybdenum\n" +
                        "• Suitable for the elderly\n" +
                        "• Ideal for those who have difficulty swallowing capsules and tablets and for individuals with digestive and absorption difficulties\n" +
                        "• Presented in a simple purified water base\n" +
                        "• Molybdenum contributes to normal sulphur amino acid metabolism\n",
                "One drop taken daily in water, with food, or directly under the tongue, or as professionally directed\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_nutrisorbliquidmolybdenum"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_beetrootextract",
                "Beetroot Extract 90 Capsules",
                "Beetroot Extract provides a potent source of freeze-dried Beetroot. Beetroot  has been part of the diet for over 2000 years and naturally contains a range of vitamins and minerals so this can be used as a gentle food-form multinutrient.\n",
                "Herbal",
                "90", 0, "Potato Maltodextrin, Beetroot Extract (Beta vulgaris), Capsule Shell (HPMC), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Citric Acid. ",
                "• Vacuum packed to enhance stability and shelf life\n" +
                        "• Naturally occurring vitamin and minerals\n" +
                        "• Suitable for use during pregnancy\n",
                "One capsule taken three times daily before food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Vacuum sealed for stability\n" +
                        "Refrigerate below 4&deg;C after opening and avoid direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_beetrootextract"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitaminb6",
                "Vitamin B6 60 Veg Capsules",
                "Our Vitamin B6 provides the active form pyridoxal-5-phosphate. It contributes to the normal metabolism of energy, protein and glycogen as well as the normal function of the nervous and immune systems and the regulation of hormonal activity.\n",
                "Vitamins",
                "60", 0, "Bulking Agent (Cellulose), Capsule Shell (HPMC), Vitamin B6 (as Pyridoxal-5-Phosphate), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate). ",
                "• Presented in the preferred biologically active form of pyridoxal-5-phosphate\n" +
                        "• Yeast-free\n" +
                        "• Vitamin B6 contributes to normal cysteine synthesis\n" +
                        "• Vitamin B6 contributes to normal energy-yielding metabolism\n" +
                        "• Vitamin B6 contributes to normal functioning of the nervous system\n" +
                        "• Vitamin B6 contributes to normal homocysteine metabolism\n" +
                        "• Vitamin B6 contributes to normal protein and glycogen metabolism\n" +
                        "• Vitamin B6 contributes to normal psychological function\n" +
                        "• Vitamin B6 contributes to normal red blood cell formation\n" +
                        "• Vitamin B6 contributes to the normal function of the immune system\n" +
                        "• Vitamin B6 contributes to the reduction of tiredness and fatigue\n" +
                        "• Vitamin B6 contributes to the regulation of hormonal activity\n",
                "One capsule taken three times daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_vitaminb6"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_reducedglutathione",
                "Reduced Glutathione 90 Capsules",
                "Reduced Glutathione contains glutathione in its biologically active reduced form. Glutathione is made from the amino acids, L-glycine, L-glutamine and L-cysteine. These capsules can be opened for flexibility of use.\n",
                "Amino Acids",
                "30 & 90", 0, "Bulking Agent (Microcrystalline Cellulose), Reduced Glutathione, Capsule Shell (HPMC), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate).  ",
                "• Capsule can be opened, ideal for those who have difficulty or prefer not to swallow capsules or tablets\n" +
                        "• Suitable for vegetarians and vegans\n",
                "One capsule taken three times daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_reducedglutathione"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_reducedglutathione",
                "Reduced Glutathione 90 Capsules",
                "Reduced Glutathione contains glutathione in its biologically active reduced form. Glutathione is made from the amino acids, L-glycine, L-glutamine and L-cysteine. These capsules can be opened for flexibility of use.\n",
                "Amino Acids",
                "30 & 90", 0, "Bulking Agent (Microcrystalline Cellulose), Reduced Glutathione, Capsule Shell (HPMC), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate).  ",
                "• Capsule can be opened, ideal for those who have difficulty or prefer not to swallow capsules or tablets\n" +
                        "• Suitable for vegetarians and vegans\n",
                "One capsule taken three times daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_reducedglutathione"),
                CompanyConstants.BIOCARE
        ));

    }
}

