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
                "biocare_hcipepsin",
                "Spirulina (Phytoplankton) 90 Caps",
                "Spirulina (formerly Sea Plasma) is a natural supplement of phytoplankton, a highly nutritious food containing trace amounts of vitamins, minerals, fatty acids and amino acids. Spirulina makes an ideal adjunct during breastfeeding.\n",
                "Herbal",
                "90", 0, "Phyto-Plankton (Athrospira platensis Algae), Capsule Shell (HPMC), Anti-Caking Agent (Magnesium Stearate).  ",
                "• Phytoplankton is a highly nutritious food containing trace amounts of vitamins, minerals, fatty acids and amino acids\n" +
                        "• Vacuum packed to maintain stability and shelf-life of ingredients\n" +
                        "• Phytoplankton is the basis of the food chain\n" +
                        "• Contains chlorophyll; the green pigment in plants\n",
                "One capsule taken three times daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Vacuum sealed for stability\n",
                ImageRetriever.createUrl("biocare_spirulina"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_spirulina",
                "HCl & Pepsin 90 Capsules",
                "HCl & Pepsin is a combination of betaine hydrochloride and pepsin encapsulated allowing for quicker release of the ingredients. HCl & Pepsin is deliberately low potency to allow flexibility in adjusting intake according to individual requirements.\n",
                "Gastrointestinals",
                "90", 0, "Betaine Hydrochloride, Potato Maltodextrin, Capsule Shell  (HPMC),  Bulking Agent (Cellulose), Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide), Pepsin. ",
                "• HCl provides the optimal environment (around pH 2) for pepsin activity\n" +
                        "• Encapsulation allows for a quicker release of the ingredients and reduces the likelihood of gastric wall irritation that can sometimes occur with tablets\n" +
                        "• Low potency therefore allowing for more flexibility in adjusting individual requirements\n",
                "One capsule taken three times daily with food, or as professionally directed.\n" +
                        "NB: HCL & Pepsin capsules should not be opened or chewed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_hcipepsin"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_enteroplex",
                "Enteroplex® 60 Capsules",
                "Enteroplex® combines licorice and cabbage extract (cabagin) with zinc, magnesium and vitamin C. Licorice has traditionally been used to facilitate the digestion.\n",
                "Gastrointestinals",
                "60", 0, "Licorice Extract (Glycyrrhiza glabra Root), Cabbage Powder (Brassica oleracea), Bulking Agent (Dicalcium Phosphate), Capsule Shell (HPMC), Corn Maltodextrin, Zinc Citrate, Vitamin C (as Magnesium Ascorbate), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate).  ",
                "• Vacuum packed to maintain full stability of cabagin (vitamin U)\n" +
                        "• Zinc contributes to normal DNA synthesis\n" +
                        "• Zinc contributes to normal acid-base metabolism\n" +
                        "• Zinc contributes to normal carbohydrate metabolism\n" +
                        "• Zinc contributes to normal macronutrient metabolism\n" +
                        "• Zinc contributes to normal metabolism of fatty acids\n" +
                        "• Zinc contributes to normal protein synthesis\n" +
                        "• Zinc and vitamin C contribute to the normal function of the immune system\n" +
                        "• Zinc contributes to the protection of cells from oxidative stress and has a role in the process of cell division\n" +
                        "• Licorice has traditionally been used to facilitate the digestion\n",
                "One capsule taken twice daily, 20 minutes before food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Vacuum sealed for stability\n",
                ImageRetriever.createUrl("biocare_enteroplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_cranberryplus",
                "Cranberry Plus 30 Capsules",
                "Cranberry Plus provides a high level of the active components from fresh cranberry with vitamin C and lactic acid bacteria.\n",
                "Herbal",
                "30", 0, "Potato Maltodextrin, Cranberry Powder (Vaccinium macrocarpon Fruit), Capsule Shell (HPMC), Vitamin C (as Ascorbic Acid), Bulking Agent (Cellulose), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Lactobacillus acidophilus.  ",
                "• Cranberry extract naturally contains components such as d-mannose, benzoic acid and quinic acid               \n" +
                        "• Vitamin C contributes to the normal function of the immune system\n" +
                        "• Vitamin C contributes to the protection of cells from oxidative stress\n" +
                        "• Contains Lactobacillus acidophilus\n",
                "One capsule taken daily before food, or as professionally directed\n",
                "If you're under medical supervision please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Refrigerate below 4&deg;C and avoid direct sunlight and heat\n" +
                        "Vacuum sealed for stability\n",
                ImageRetriever.createUrl("biocare_cranberryplus"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_microcellnutriguardplus",
                "MicroCell® NutriGuard® Plus (Antioxidant) Caps",
                "Vitamins A, C, zinc and selenium contribute to the normal function of the immune system and vitamin E, C, zinc and selenium are antioxidants that contribute to the protection of cells from oxidative stress.\n\n",
                "Antioxidants",
                "30 & 60 & 90", 0, "Vitamin C (as Ascorbic Acid), Capsule Shell (HPMC & Colours [Red Iron Oxide & Titanium Dioxide]), Vitamin E (as D-Alpha Tocopheryl Acetate), Silicon Dioxide, Alpha Lipoic Acid, Modified Corn Starch, Zinc Citrate, Modified Tapioca Starch, Soya Oil, Olive Oil, Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Beta Carotene, Acacia Gum, Lycopene (Lycopersicon esculentum Fruit), Vitamin A (as Retinyl Palmitate), Antioxidants (Natural Mixed Tocopherols, Ascorbic Acid & Ascorbyl Palmitate), Sodium Selenite, Sunflower Oil. ",
                "• Micellised by the unique MicroCell® process to enhance absorption and improve bioavailability\n" +
                        "• Vitamins A and C along with zinc and selenium contribute to the normal function of the immune system\n" +
                        "• Vitamin E, C, zinc and selenium are antioxidants that contribute to the protection of cells from oxidative stress\n" +
                        "• Vitamin A contributes to the maintenance of normal skin and vision\n" +
                        "• Vitamin A helps contribute to the maintenance of mucous membranes\n" +
                        "• Zinc contributes to normal DNA synthesis\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_microcellnutriguardplus"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_colonguard",
                "ColonGuard® 60 Capsules",
                "ColonGuard® provides a high potency combination of short chain fatty acids, garlic, aloe vera and biotin.\n",
                "Gastrointestinals",
                "60", 0, "Magnesium Caprylate, Calcium Magnesium Butyrate, Garlic Powder (Allium sativum Clove), Capsule Shell (HPMC), Magnesium Citrate, Aloe vera (Aloe barbadensis Leaf), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Medium Chain Triglycerides, Biotin. ",
                "• Biotin supports the maintenance of normal mucous membranes\n" +
                        "• Butyric and caprylic acid are short chain fatty acids\n" +
                        "• Garlic contributes to normal immune function\n" +
                        "• Garlic has antibacterial properties\n",
                "Two capsules taken daily with food, or as professionally directed.\n",
                "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_colonguard"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_p5pcomplex",
                "P-5-P Complex 60 Capsules",
                "P-5-P Complex is a combination of vitamin B6 with cofactors zinc, magnesium and vitamin B2. Vitamin B6 contributes to the regulation of hormonal activity and normal functioning of the nervous system.\n",
                "Vitamins",
                "60", 0, "Magnesium Citrate, Capsule Shell (HPMC), Vitamin B6 (as Pyridoxal-5-Phosphate), Zinc Citrate, Riboflavin, Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide).  ",
                "• Vitamin B6 contributes to the regulation of hormonal activity\n" +
                        "• Citrates are particularly beneficial for those with low stomach acid as they require little or no acidification prior to absorption\n" +
                        "• Zinc contributes to the maintenance of normal hair, skin, nails, bones and vision\n" +
                        "• Vitamin B6 contributes to normal cysteine synthesis\n" +
                        "• Vitamin B2 and  B6 contribute to normal energy-yielding metabolism and contributes to the reduction of tiredness and fatigue\n" +
                        "• Vitamin B2 and B6 contribute to normal functioning of the nervous system\n" +
                        "• Vitamin B6 contributes to normal homocysteine metabolism\n" +
                        "• Vitamin B6 contributes to normal protein and glycogen metabolism\n" +
                        "• Vitamin B6 contributes to normal psychological function\n" +
                        "• Vitamin B6 contributes to normal red blood cell formation\n" +
                        "• Vitamin B6 and zinc contribute to the normal function of the immune system\n" +
                        "• Zinc contributes to normal fertility and reproduction\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "Long term intake of amounts greater than 10mg vitamin B6 daily may lead to mild tingling and numbness\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_p5pcomplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_b_plex",
                "B Plex (B vitamins) 60 Caps",
                "B Plex is a specialised formulation of B vitamins which excludes vitamin B12 and folic acid. B vitamins contribute to the release of energy from foods, the reduction of tiredness and fatigue and supporting normal physiological function.\n",
                "Vitamins",
                "60", 0, "Capsule Shell (HPMC), Magnesium Gluconate, Thiamine (as Thiamine Hydrochloride), Vitamin B6 (as Pyridoxine Hydrochloride & Pyridoxal-5-Phosphate), Pantothenic Acid (as Calcium Pantothenate), Choline Bitartrate, Inositol, Para Amino Benzoic Acid, Riboflavin, Niacin (as Nicotinamide), Vitamin C (as Magnesium Ascorbate), Magnesium Citrate, Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Biotin. ",
                "• Contains no vitamin B12 or folic acid\n" +
                        "• Vitamins B2, B6, C and biotin contribute to normal functioning of the nervous system\n" +
                        "• Vitamin B1, B3 and B6 contribute to normal psychological function\n" +
                        "• Vitamin B1, B2, B3, B5 and B6 contribute to normal energy-yielding metabolism\n" +
                        "• Vitamin B2, B3, B5, and B6 and C contribute to the reduction of tiredness and fatigue\n" +
                        "• Thiamine contributes to the normal function of the heart\n" +
                        "• High potency for optimal nutritional impact\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "Long term intake of amounts greater than 10mg vitamin B6 daily may lead to mild tingling and numbness\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_b_plex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_osteoplex",
                "Osteoplex (Bone Health Complex) 90 Caps",
                "Osteoplex is a combination of magnesium and calcium in their bioavailable citrate form with boron and vitamins D & K. Vitamins K, D, calcium and magnesium contribute to maintenance of normal bones.\n",
                "Specific Nutrient Complexes",
                "90", 0, "Magnesium Citrate, Calcium Citrate, Capsule Shell (HPMC), Bulking Agent (Microcrystalline Cellulose) Anti-Caking Agents (Ascorbyl Palmitate & Silicon Dioxide), Modified Corn Starch, Corn Starch, Maltodextrin, Beta Carotene, Sodium Borate, Vitamin D3 (as Cholecalciferol), Sucrose, Antioxidant (DL-Alpha Tocopherol) Vitamin K2 (as Menaquinone-7). ",
                "• Vitamin D and calcium are needed for the maintenance of normal bones and teeth\n" +
                        "• Magnesium contributes to normal muscle function and the maintenance of normal teeth\n" +
                        "• Vitamin D contributes to normal absorption/utilisation of calcium and phosphorus\n" +
                        "• Vitamin K & calcium contributes to normal blood clotting\n" +
                        "• Vitamin K & magnesium contribute to the maintenance of normal bones\n" +
                        "• Magnesium contributes to normal protein synthesis\n" +
                        "• Magnesium contributes to normal psychological function\n" +
                        "• Vitamin D contributes to the maintenance of normal muscle function\n" +
                        "• Citrates require little acidification prior to absorption\n", "One capsule taken three times daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_osteoplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitasorb_a",
                "Vitasorb® A (Liquid Vitamin A) 15ml",
                "Vitasorb® A is a simple liquid vitamin A preparation providing 2500iu per drop. Vitasorb® vitamins can be taken under the tongue or added to drinks. The Vitasorb® range is particularly suited to those with dietary intolerances.\n",
                "Vitamins",
                "15ml", 0, "Extra Virgin Olive Oil, Vitamin A (as Retinyl Palmitate), Antioxidant (DL-Alpha Tocopherol) ",
                "• Liquid vitamin A ideal for those who have difficulty swallowing capsules and tablets\n" +
                        "• Can be taken sublingually (under the tongue) for those with gastrointestinal or malabsorption problems\n" +
                        "• Provides 2500iu vitamin A per drop                                                                                   \n" +
                        "• Suitable for the elderly\n" +
                        "• Vitamin A contributes to normal iron metabolism\n" +
                        "• Vitamin A contributes to the maintenance of normal mucous membranes\n" +
                        "• Vitamin A contributes to the maintenance of normal skin\n" +
                        "• Vitamin A contributes to the maintenance of normal vision\n" +
                        "• Vitamin A contributes to the normal function of the immune system\n" +
                        "• Vitamin A has a role in the process of cell specialisation\n" +
                        "• Presented in a simple, hypoallergenic olive oil base\n", "One drop taken daily in water with food, or directly under the tongue, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_vitasorb_a"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitasorb_b",
                "Vitasorb® B (Liquid B vitamins) 15ml",
                "Vitasorb® B is a highly absorbable liquid B vitamin preparation ideal for individuals with digestive and absorption difficulties. Vitasorb® B can be taken  under the tongue or added to drinks.\n",
                "Vitamins",
                "15ml", 0, "Preservatives (Glycerol & Sodium Bicarbonate), Purified Water, Niacin (as Nicotinamide), Vitamin B6 (as Pyridoxine Hydrochloride), Riboflavin (as Riboflavin-5-Phosphate), Thiamine (as Thiamine Hydrochloride), Sodium Carbonate, Folic Acid, Vitamin B12 (as Cyanocobalamin).  ",
                "• Provides liquid B vitamins with a high absorption rate\n" +
                        "• Ideal for those who have difficulty swallowing capsules and tablets and for individuals with digestive and absorption difficulties.\n" +
                        "• Presented in a simple, hypoallergenic purified water base\n" +
                        "• Vitamins B2, B6, B12 contribute to normal functioning of the nervous system\n" +
                        "• Vitamin B1, B3, B6 and B12 contribute to normal psychological function\n" +
                        "• Vitamin B1, B2, B3 and B6 contribute to normal energy-yielding metabolism\n" +
                        "• Vitamin B2, B3, B6 and B12 contribute to the reduction of tiredness and fatigue\n", "Ten drops taken daily in water, with food, or directly under the tongue or as professionally directed.\n" +
                "\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_vitasorb_b"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitasorb_c",
                "Vitasorb® C (Liquid vitamin C) 15ml",
                "Vitasorb® C is a liquid vitamin C preparation providing 30mg per daily intake in a hypoallergenic purified water base.  Vitasorb® C is a highly absorbable liquid preparation that can be mixed into juice, milk or water.\n",
                "Vitamins",
                "15ml", 0, "Purified Water, Vitamin C (as Ascorbic Acid).  ",
                "• Provides liquid vitamin C with a high absorption rate\n" +
                        "• Ideal for those who have difficulty swallowing capsules and tablets\n" +
                        "• Can be taken sublingually (under the tongue) for those with gastrointestinal problems\n" +
                        "• Ideal for individuals with digestive and absorption difficulties\n" +
                        "• Suitable for the elderly\n" +
                        "• Vitamin C contributes to normal collagen formation for the normal function of blood vessels, bones, cartilage, gums, skin and teeth.\n" +
                        "• Vitamin C contributes to normal energy-yielding metabolism\n" +
                        "• Vitamin C contributes to normal functioning of the nervous and immune system\n" +
                        "• Vitamin C contributes to normal psychological function\n" +
                        "• Vitamin C contributes to the protection of cells from oxidative stress\n" +
                        "• Vitamin C contributes to the reduction of tiredness and fatigue\n" +
                        "• Vitamin C contributes to the regeneration of the reduced form of vitamin E\n" +
                        "• Vitamin C increases iron absorption\n" +
                        "• Presented in a simple, hypoallergenic purified water base.\n", "Four drops taken daily in water with food, or directly under the tongue, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_vitasorb_c"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitasorb_d",
                "Vitasorb® D (Liquid Vitamin D) 15ml",
                "Vitasorb® D provides 50iu per daily intake and is an easy to take, highly absorbable liquid vitamin D preparation which can be taken under the tongue or added to drinks.\n",
                "Vitamins",
                "15ml", 0, "Extra Virgin Olive Oil, Medium Chain Triglycerides, Vitamin D3 (as Cholecalciferol). ",
                "• Provides liquid vitamin D to facilitate absorption\n" +
                        "• 12.5iu of vitamin D per drop\n" +
                        "• Ideal for those who have difficulty or prefer not to swallow capsules or tablets\n" +
                        "• Can be taken sublingually (under the tongue) for those with gastrointestinal difficulties\n" +
                        "• Ideal for individuals with malabsorption\n" +
                        "• Suitable for the elderly\n" +
                        "• Vitamin D supplementation may be beneficial during winter months\n" +
                        "• Vitamin D contributes to normal absorption/utilisation of calcium and phosphorus\n" +
                        "• Vitamin D contributes to normal blood calcium levels\n" +
                        "• Vitamin D contributes to the maintenance of normal bones and normal muscle function\n" +
                        "• Vitamin D contributes to the maintenance of normal teeth\n" +
                        "• Vitamin D contributes to the normal function of the immune system\n" +
                        "• Vitamin D has a role in the process of cell division\n" +
                        "• Presented in a simple, hypoallergenic olive oil base\n", "Four drops taken daily in water with food, or directly under the tongue, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_vitasorb_d"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitasorb_e",
                "Vitasorb® E (Liquid Vitamin E) 15ml",
                "Vitasorb® E is a highly absorbable liquid providing 100iu per daily intake. It can be taken under the tongue or added to drinks. The Vitasorb® range is ideal for those for whom capsules and tablets are not suitable.\n",
                "Vitamins",
                "15ml", 0, "Vitamin E (as D-Alpha Tocopherol Acetate), Extra Virgin Olive Oil",
                "• Provides 100iu per 5 drops\n" +
                        "• Liquid form makes it ideal for those who have difficulty or prefer not to swallow capsules or tablets and for individuals with digestive and absorption difficulties\n" +
                        "• Presented in a simple, hypoallergenic olive oil base\n" +
                        "• Vitamin E contributes to the protection of cells from oxidative stress\n", "Five drops taken daily in water with food or directly under the tongue, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_vitasorb_e"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitasorb_multivitamins",
                "Vitasorb® Multivitamins (Liquid Multivitamin) 30ml",
                "Vitasorb® Multivitamins includes a range of vitamins in a highly absorbable liquid form. They can be taken under the tongue or added to drinks. The Vitasorb® range is ideal for those for whom capsules and tablets are unsuitable.\n\n",
                "Vitamins",
                "15ml", 0, "Purified Water, Preservatives (Glycerol & Potassium Sorbate), Modified Tapioca Starch, Vitamin E (as D-Alpha Tocopherol Acetate), Vitamin C (as Ascorbic Acid), Niacin (as Nicotinamide), Vitamin B6 (as Pyridoxine Hydrochloride), Thamine (as Thiamine Hydrochloride), Riboflavin ( as Riboflavin-5-Phosphate Sodium), Vitamin A (as Retinyl Palmitate), Folic Acid, Acacia Gum, Sunflower Oil, Sucrose, Corn Starch, Antioxidant (Natural Mixed Tocopherols), Vitamin B12 (as Hydroxycobalamin), Vitamin D2 (as Ergocalciferol). ",
                "• Provides liquid multivitamins to facilitate absorption\n" +
                        "• Ideal for those who have difficulty or prefer not to swallow capsules or tablets\n" +
                        "• Can be taken sublingually (under the tongue) for those with gastrointestinal difficulties\n" +
                        "• Ideal for individuals with malabsorption\n" +
                        "• Suitable for the elderly\n" +
                        "• Vitamin E contributes to the protection of cells from oxidative stress\n" +
                        "• Vitamin C contributes to the normal function of the immune system including before and after physical exercise\n" +
                        "• Vitamin C contributes to normal collagen formation for the normal function of blood vessels, bones, cartilage, gums, skin and teeth\n" +
                        "• Vitamin D contributes to the maintenance of normal teeth\n" +
                        "• Vitamin D contributes to the normal function of the immune system\n", "Fifteen drops taken twice daily in water with food, or taken directly under the tongue, or as professionally directed.\n" +
                "\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Long term intake of amounts greater than 10mg vitamin B6 daily may lead to mild tingling and numbness\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_vitasorb_multivitamins"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitasorb_b12",
                "Vitasorb® B12 (Liquid Vitamin B12) 15ml",
                "Vitasorb® B12 provides vitamin B12 in a highly absorbable, liquid form. Vitamin B12 contributes to the normal functioning of the nervous system, metabolism and psychological function as well as contributing to the reduction of tiredness and fatigue.\n",
                "Vitamins",
                "15ml", 0, "Purified Water, Preservatives (Citric Acid & Potassium Sorbate), Vitamin B12 (as Hydroxycobalamine), ",
                "• Provides liquid vitamin B12 to facilitate absorption\n" +
                        "• Vitamin B12 is essential for the production of healthy red blood cells\n" +
                        "• Vitamin B12 is required for the maintenance of healthy nerve function\n" +
                        "• Ideal for those who have difficulty or prefer not to swallow capsules or tablets and can be taken sublingually (under the tongue) for those with gastrointestinal problems\n" +
                        "• Suitable for the elderly\n" +
                        "• Vitamin B12 contributes to normal energy-yielding metabolism\n" +
                        "• Vitamin B12 contributes to normal functioning of the nervous system\n" +
                        "• Vitamin B12 contributes to normal homocysteine metabolism\n" +
                        "• Vitamin B12 contributes to normal psychological function\n" +
                        "• Vitamin B12 contributes to normal red blood cell formation\n" +
                        "• Vitamin B12 contributes to the normal function of the immune system\n" +
                        "• Vitamin B12 contributes to the reduction of tiredness and fatigue\n" +
                        "• Vitamin B12 has a role in the process of cell division\n", "Five drops twice daily in water with food, or directly under the tongue, or as professionally directed.\n" +
                "\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "Store below 25oC in a dry place away from direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_vitasorb_b12"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_nutrisorbliquidzincplusascorbate",
                "Nutrisorb® Liquid Zinc Plus Ascorbate 30ml",
                "Nutrisorb® Liquid Zinc Plus Ascorbate is a highly absorbable, liquid zinc combined with vitamin C which can be taken under the tongue or in drinks.\n",
                "Vitamins",
                "30ml", 0, "Purified Water, Zinc Ascorbate, Preservative (Ascorbic Acid). ",
                "• Highly bio-available liquid formulation, ideal for those who have difficulty or prefer not to swallow capsules or tablet\n" +
                        "• Presented as a simple hypoallergenic water base    \n" +
                        "• Suitable for the elderly\n" +
                        "• Zinc and vitamin C contribute to the normal function of the immune system\n" +
                        "• Zinc and vitamin C are antioxidants that contribute to the protection of cells from oxidative stress\n" +
                        "• Zinc contributes to normal carbohydrate metabolism\n" +
                        "• Zinc contributes to normal cognitive function\n" +
                        "• Zinc contributes to normal fertility and reproduction\n" +
                        "• Zinc contributes to normal macronutrient metabolism\n" +
                        "• Zinc contributes to normal metabolism of vitamin A\n" +
                        "• Vitamin C contributes to the reduction of tiredness and fatigue\n" +
                        "• Zinc contributes to normal DNA synthesis\n", "Ten drops taken daily in water with food, or directly under the tongue, or as professionally directed.\n" +
                "\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_nutrisorbliquidzincplusascorbate"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitasorb_b6",
                "Vitasorb® B6 (Liquid vitamin B6) 30ml",
                "Vitasorb® B6 provides vitamin B6 in a highly absorbable, liquid form.  Vitamin B6 contributes to many functions such as normal functioning of the immune and nervous systems, energy metabolism and reducing tiredness and fatigue.\n",
                "Vitamins",
                "30ml", 0, "Glycerol, Purified Water, Vitamin B6 (as Pyridoxine Hydrochloride), Preservative (Potassium Sorbate)  ",
                "• Provides liquid vitamin B6 to facilitate absorption\n" +
                        "• Ideal for those who have difficulty or prefer not to swallow capsules or tablets\n" +
                        "• Can be taken sublingually (under the tongue) for those with gastrointestinal problems\n" +
                        "• Ideal for individuals with digestive and absorption difficulties\n" +
                        "• Suitable for the elderly\n" +
                        "• Vitamin B6 contributes to normal cysteine synthesis\n" +
                        "• Vitamin B6 contributes to normal energy-yielding metabolism\n" +
                        "• Vitamin B6 contributes to normal functioning of the nervous system\n" +
                        "• Vitamin B6 contributes to normal homocysteine metabolism\n" +
                        "• Vitamin B6 contributes to normal protein and glycogen metabolism\n" +
                        "• Vitamin B6 contributes to normal psychological function\n" +
                        "• Vitamin B6 contributes to normal red blood cell formation\n" +
                        "• Vitamin B6 contributes to the normal function of the immune system\n" +
                        "• Vitamin B6 contributes to the reduction of tiredness and fatigue\n" +
                        "• Vitamin B6 contributes to the regulation of hormonal activity\n" +
                        "• Presented in a simple, hypoallergenic purified water base\n", "Ten drops taken daily in water with food, or directly under the tongue, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "Store below 25oC in a dry place away from direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_vitasorb_b6"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_quercetinplus",
                "Quercetin Plus 90 Capsules",
                "Quercetin Plus combines the nutrients quercetin, bromelain and vitamin C.  This useful formulation also includes vitamin C for its contribution to the normal function of the immune system and to the protection of cells from oxidative stress.\n",
                "Antioxidants",
                "90", 0, "Quercetin (Sophorae japonica Flower), Capsule Shell (HPMC), Potato Maltodextrin, Bulking Agent (Cellulose), Nettle Extract 4:1 (Urtica dioica Leaf), Vitamin C (as Ascorbic Acid), Bromelain, Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate).  ",
                "• Vitamin C contributes to the normal function of the immune system\n" +
                        "• Vitamin C is an antioxidant that contributes to the protection of cells from oxidative stress\n" +
                        "• Bromelain is sourced from pineapple\n" +
                        "• Nettle supports venous circulation\n" +
                        "• Nettle may support of the body's defences and support the immune system\n", "One capsule taken three times daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "Caution is advised with anticoagulant medication\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_quercetinplus"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_magnesiumalate",
                "Magnesium Malate 90 Capsules",
                "Magnesium Malate provides a complex of malic acid and magnesium. Magnesium contributes to a number of important functions including normal muscle function and maintenance of normal bones and teeth as well as contributing to normal energy metabolism.\n",
                "Minerals",
                "90", 0, "Magnesium Malate, Capsule Shell  (HPMC), Bulking Agent (Cellulose), Anti-Caking Agents (Silicon Dioxide &  Magnesium Stearate). ",
                "• Magnesium contributes to normal energy-yielding metabolism and the reduction of tiredness and fatigue\n" +
                        "• Magnesium contributes to electrolyte balance\n" +
                        "• Magnesium contributes to normal functioning of the nervous system\n" +
                        "• Magnesium contributes to normal muscle function\n" +
                        "• Magnesium contributes to normal protein synthesis\n" +
                        "• Magnesium contributes to normal psychological function\n" +
                        "• Magnesium contributes to the maintenance of normal bones and teeth\n" +
                        "• Magnesium has a role in the process of cell division\n" +
                        "• Magnesium Malate may be particularly useful for sportspeople or those who undertake extensive exercise\n", "One capsule taken three times daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_magnesiumalate"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_biocidin",
                "Biocidin® (Grapefruit Seed Extract) 90 Caps",
                "Biocidin® provides a concentrated form of grapefruit seed extract.The recommended intake provides 225mg of grapefruit seed extract.\n",
                "Gastrointestinals",
                "90", 0, "Bulking Agent (Microcrystalline Cellulose), Grapefruit Oil (Citrus grandis, Skin, Pulp and Seed), Capsule Shell (HPMC), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate).  ",
                "• Provides a concentrated source of grapefruit oil\n" +
                        "• The active grapefruit oil is derived from the seeds, pulp and white membranes of the grapefruit\n" +
                        "• A higher strength grapefruit oil extract Biocidin® Forte is also available as well as a Liquid Biocidin® which is ideal for those who have difficulty swallowing capsules\n", "One capsule taken three times daily with food or as professionally directed.\n",
                "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_biocidin"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_biocidinforte",
                "Biocidin® Forte (Grapefruit Seed Extract) 90 Caps",
                "Biocidin® provides a concentrated form of grapefruit seed extract.  The recommended intake provides 450mg of grapefruit seed extract.\n",
                "Gastrointestinals",
                "90", 0, "Grapefruit Oil (Citrus grandis Skin, Pulp & Seed), Capsule Shell (HPMC), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Bulking Agent (Cellulose). ",
                "• Provides a concentrated source of grapefruit oil\n" +
                        "• The active grapefruit oil is derived from the seeds, pulp and white membranes of the grapefruit\n" +
                        "• A lower strength grapefruit oil extract Biocidin® is also available as well as a Liquid Biocidin® which is ideal for those who have difficulty swallowing capsules\n", "One capsule taken three times daily with food or as professionally directed.\n",
                "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_biocidinforte"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_liquidbiocidin",
                "Liquid Biocidin® 15ml",
                "Liquid Biocidin® provides concentrated grapefruit seed extract in liquid form which is particularly suited to those who have difficulties taking tablets and capsules. It is safe for use during pregnancy.\n",
                "Gastrointestinals",
                "15ml", 0, "Purified water, Modified Tapioca Starch, Preservatives (Glycerol & Potassium Sorbate), Grapefruit Oil (Citrus grandis Pulp, Skin & Seed), Antioxidant (Natural Mixed Tocopherols), Sunflower Oil. ",
                "• Provides a concentrated liquid source of grapefruit seed extract\n" +
                        "• Liquid format is ideal for those who are unable or prefer not to swallow capsules or tablets\n" +
                        "• If diluted, it can be used as a mouthwash, throat gargle or vaginal douche\n" +
                        "• The active grapefruit oil is derived from the seeds, pulp and white membranes of the grapefruit\n" +
                        "• Higher strength encapsulated grapefruit oil extracts Biocidin® and Biocidin® Forte are also available\n", "Three drops twice daily in water with food, or as professionally directed.\n",
                "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_liquidbiocidin"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_n_acetylcysteine",
                "N-Acetyl Cysteine 90 Caps",
                "N-Acetyl Cysteine (formerly Bio-Cysteine) provides cysteine which is a sulphur amino acid.  N-Acetyl Cysteine contains 500mg N-acetyl cysteine per capsule.\n",
                "Amino Acids",
                "90", 0, "N-Acetyl Cysteine, Capsule Shell (HPMC), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate). ",
                "• Provides sulphur containing amino acid N-acetyl cysteine\n", "One capsule taken three times daily before food or as professionally directed.\n" +
                "NB: N-Acetyl Cysteine has a strong sulphur like smell which is completely normal\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_n_acetylcysteine"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_biocarbonate",
                "BioCarbonate 90 Caps",
                "BioCarbonate provides carbonates in the form of sodium and potassium salts.\n",
                "Gastrointestinals",
                "90", 0, "Sodium Bicarbonate, Potassium Bicarbonate, Capsule (HPMC), Bulking Agent (Cellulose), Anti-Caking Agent (Magnesium Stearate).   ",
                "• Combination of sodium and potassium bicarbonate\n", "One capsule taken three times daily 30 minutes after food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_biocarbonate"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitasorb_c0q10",
                "Vitasorb® CoQ10 (Liquid CoQ10) 30ml",
                "Vitasorb® CoQ10 provides 20mg co-enzyme Q10 along with vitamin E. These fat soluble nutrients are easy to absorb in this liquid form. Vitamin E contributes to the protection of cells from oxidative stress.\n",
                "Vitamins",
                "30ml", 0, "Purified Water, Modified Tapioca Starch, Grapeseed Oil, Vitamin E (as D-Alpha Tocopheryl Acetate), Co Enzyme Q10, Sweet Orange Oil (Providing Limonene), Antioxidants (Ascorbic Acid & Natural Mixed Tocopherols), Sunflower Oil, Preservative (Potassium Sorbate). ",
                "• Detergent and surfactant free\n" +
                        "• Can be diluted in water and taken sublingually (under the tongue) for those with gastrointestinal or malabsorption difficulties\n" +
                        "• Liquid format is ideal for those who have difficulty or prefer not to swallow capsules or tablets\n" +
                        "• Drop form enables flexible dose management for at lower doses (minimum 1mg)\n" +
                        "• Suitable for the elderly\n" +
                        "• Vitamin E contributes to the protection of cells from oxidative stress\n", "Ten drops taken twice a day in water with food, or directly under the tongue, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_vitasorb_c0q10"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_ginkgoplusblackcurrant",
                "Ginkgo plus Blackcurrant - 60 Capsules",
                "",
                "Herbal",
                "60", 0, "Ginkgo Biloba Extract (Ginkgo biloba Leaf), Blackcurrant Extract (Ribes nigrum Leaf), Capsule Shell (HPMC), Bulking Agent (Microcrystalline Cellulose), Anti-Caking Agents (Ascorbyl Palmitate & Silicon Dioxide), Maltodextrin.  ",
                "• Ginkgo biloba is the world's oldest living species of tree and the leaves of the tree are used in modern herbal medicine. Medicinal use of ginkgo can be traced back almost 5,000 years in Chinese herbal medicine\n" +
                        "• Ginkgo helps the maintenance of good cognitive function\n" +
                        "• Ginkgo extract contains ginkgo flavone glycosides and terpene lactones. Ginkgo flavone glycosides typically make up 24% of the extract and terpene lactones make up approximately 6%\n" +
                        "• Ginkgo plus Blackcurrant contains an extremely concentrated form of ginkgo (300mg at 50:1 extract) equivalent to 15000mg of concentrated ginkgo powder\n" +
                        "• Blackcurrant is naturally rich in vitamin C and anthocyanidins\n" +
                        "• Suitable for vegetarians and vegans\n" +
                        "• 30 days' supply at 2 capsules per day\n", "One capsule taken twice daily with food, or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "If you are under medical supervision, please consult a doctor before use.\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding.\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_ginkgoplusblackcurrant"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_cysteinecomplex",
                "Cysteine Complex - 60 Capsules",
                "",
                "Specific Nutrient Complexes",
                "60", 0, "Broccoli Powder (Brassica oleracea italica Florets), Broccoli Sprout Powder (Brassica oleracea italica), Bulking Agent (Cellulose), Capsule Shell (Hydroxypropyl Methylcellulose), Alpha Lipoic Acid, N-Acetyl L-Cysteine, Indole-3-Carbinol, Pomegranate Extract (Punica granatum Hulls), Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide), Microcrystalline Cellulose, Sunflower Lecithin, Potassium Molybdate. ",
                "", "One or two capsules taken daily with food, or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "If you are under medical supervision, please consult a doctor before use.\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding.\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_cysteinecomplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_carbozyme",
                "Carbozyme® (Carbohydrate Enzyme Complex) 90 Caps",
                "A formulation of this practitioners' favourite now including alpha galactosidase, amylase and cellulase. This has been developed to ensure that it is still effective in the harsh, acidic conditions of the stomach.\n",
                "Digestive Enzymes",
                "90",
                0,
                "Bulking Agent (Cellulose), Potato Maltodextrin, Capsule Shell (HPMC), Alpha Galactosidase, Amylase, Anti-Caking Agent (Magnesium Stearate), Cellulase, Soy Protein Isolate. ",
                "• Contains Alpha Galactosidase\n" +
                        "• Acid stable against stomach pH\n" +
                        "• Able to retain digestive capability over a wide range of pH variances\n" +
                        "• Vacuum packed to maintain stability, shelf-life and to prevent moisture uptake\n" +
                        "• All enzymes are from vegetable sources\n",
                "One capsule taken with foods containing carbohydrates or as professionally directed.\n",
                "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_carbozyme"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_lipozyme",
                "Lipozyme® (Fat Enzyme Complex) 90 Caps",
                "Lipozyme® provides a high potency fat digesting enzyme product consisting of lipase enzyme in a vegetable capsule form. This has been developed to ensure that it is still effective in the harsh conditions of the stomach.\n",
                "Digestive Enzymes",
                "90",
                0,
                "Bulking Agent (Cellulose), Capsule Shell (HPMC), Lipase, Potato Maltodextrin, Anti-Caking Agent (Magnesium Stearate). ",
                "• Acid stable against stomach pH\n" +
                        "• Vacuum packed to maintain stability and shelf-life of the enzyme\n" +
                        "• Able to retain digestive capability over a wide range of pH variances\n" +
                        "• For full allergen information, scroll over label above\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Vacuum sealed for stability\n",
                ImageRetriever.createUrl("biocare_lipozyme"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_microcellcoq10plusomega",
                "MicroCell® CoQ10 Plus Omega 60 Caps",
                "MicroCell® CoQ10 Plus Omega combines co-enzyme Q10 with concentrated fish oil, vitamins E and C. The oils and CoQ10 are micellised to increase absorption and encapsulated within a natural chlorophyll capsule to help reduce light-sensitive oxidation.\n",
                "Fatty Acids",
                "60",
                0,
                "Potato Maltodextrin, Fish Oil1, Modified Tapioca Starch, Capsule Shell [HPMC & Colours (Titanium Dioxide & Copper Chlorophyllin)], Coenzyme Q10, Lemon Puree, Olive Oil, Modified Maize Starch, Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide), Vitamin E (as D-Alpha Tocopheryl Acetate2), Vitamin C (as Ascorbic Acid), Silicon Dioxide, Antioxidants (Ascorbic Acid, Ascorbyl Palmitate & Natural Mixed Tocopherols1), Sunflower Oil, Acacia Gum, Preservative (Sulphur Dioxide3). ",
                "• Formerly known as MicroCell LipoPlex\n" +
                        "• Provides a combination of highly bioavailable micellised CoQ10 and fish oil\n" +
                        "• MicroCell® nutritional oils are encapsulated in chlorophyll vegetable capsules to maintain stability\n" +
                        "• Vitamin C and vitamin E contribute to the protection of cells from oxidative stress\n" +
                        "• MicroCell® CoQ10 Plus Omega provides a combination of highly bio-available micellised CoQ10 and fish oil\n" +
                        "• MicroCell® CoQ10 Plus Omega provides fish oil from anchovies and sardines and is free from detectable PCBs and contaminants\n" +
                        "• For full allergen information, scroll over label above\n",
                "One capsule taken twice daily with food, or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Vacuum sealed for stability\n" +
                        "Caution is advised with anti-coagulant medication\n",
                ImageRetriever.createUrl("biocare_microcellcoq10plusomega"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_celery_seed",
                "Celery Seed 60 Capsules",
                "Celery Seed provides a potent source of the popular celery seed extract.  This formula contains 500mg per daily intake. Celery seed contains two volatile oils and a substance called apiol.\n",
                "Herbal",
                "60",
                0,
                "Celery Seed 10:1 (Apium graveolens Seed), Capsule Shell (HPMC) , Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate).  ",
                "• Celery seed is naturally rich in alkaline elements\n" +
                        "• Celery seed provides a natural source of bio-available vitamins and minerals\n" +
                        "• For full allergen information, scroll over label above\n",
                "One capsule taken twice daily with food, or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_celery_seed"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vyta_myn_complex",
                "VytaMyn Complex (Multivitamin & Mineral) 30 Caps",
                "VytaMyn Complex is a specialist blend of vitamins and minerals with the benefit of extra vitamin B5 and vitamin A as well as Siberian ginseng offering a convenient, professional level multinutrient.\n",
                "Multi-Nutrients",
                "30 & 60 & 90",
                0,
                "Pantothenic Acid (as Calcium Pantothenate), Capsule Shell (HPMC), Vitamin E (as D-Alpha Tocopheryl Acetate), Modified Tapioca Starch, Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Zinc Citrate, Linseed Oil, Potato Maltodextrin, Magnesium Citrate, Citrus Bioflavonoids (Citrus sinensis Fruit), L-Cysteine Hydrochloride, Vitamin B6 (as Pyridoxine Hydrochloride), Chondriotin Sulphate (Fish), Siberian Ginseng (Eleutherococcus senticosus Root), Alginate, Soy Protein, Acacia Gum, Niacin (as Nicotinamide), Thiamine (as Thiamine Hydrochloride), Beta Carotene, Riboflavin, Sunflower Oil, Biotin, Manganese Citrate, Antioxidants (Ascorbic Acid, Ascorbyl Palmitate & Natural Mixed Tocopherols), Chromium Picolinate, Vitamin A (as Retinyl Palmitate), Sucrose, Folic Acid, Sodium Molybdate, Copper Citrate, Vitamin K (as Phylloquinone), Corn Starch, Sodium Selenite, Potassium Iodide, Vitamin B12 (as Hydroxycobalamin), Vitamin D (as Ergocalciferol). ",
                "• Comprehensive multivitamin and mineral formula\n" +
                        "• Vitamin E contributes to the protection of cells from oxidative stress\n" +
                        "• Vitamin B6, B12, D and zinc contribute to the normal function of the immune system\n" +
                        "• Vitamin D contributes to the maintenance of normal bones, muscle function and teeth\n" +
                        "• Pantothenic acid contributes to normal mental performance, synthesis and metabolism of steroid hormones, and some neurotransmitters\n" +
                        "• B vitamins are required for the release of energy from food, for the healthy function of the nervous system\n" +
                        "• Manganese is needed for a healthy functioning nervous system\n" +
                        "• Zinc contributes to a normal function of the immune system and to the protection of cell constituents from oxidative damage\n" +
                        "• Siberian ginseng is an antioxidant that may help with energy supply\n" +
                        "• For full allergen information, scroll over label above\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "Long term intake of amounts greater than 10mg vitamin B6 daily may lead to mild tingling and numbness\n" +
                        "Do not take if you are pregnant or if you are likely to become pregnant except on the advice of a doctor or ante-natal clinic\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product contains vitamin A\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_vyta_myn_complex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_permatrol",
                "Permatrol® 90 Capsules",
                "Permatrol® is a unique combination that provides key nutrients such as L-glutamine, N-acetyl glucosamine and lactobacillus acidophilus.  This specialist formulation also includes vitamin E and gamma oryzanol.\n",
                "Gastrointestinals",
                "90",
                0,
                "L-Glutamine, N-Acetyl Glucosamine (Crustaceans), Capsule Shell (HPMC), Gamma Oryzanol, Silicon Dioxide, Vitamin E (as D-Alpha Tocopherol Acetate), Lactobacillus acidophilus, Anti-Caking Agent (Magnesium Stearate), Acacia Gum. ",
                "• Vitamin E contributes to the protection of cells from oxidative stress\n" +
                        "• Gamma oryzanol is a naturally occurring component of rice bran oil\n" +
                        "• Presented as a capsule which can be opened if more convenient\n" +
                        "• For full allergen information, scroll over label above\n",
                "One capsule taken three times daily before food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Vacuum sealed for stability\n",
                ImageRetriever.createUrl("biocare_permatrol"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_nag",
                "N.A.G. (N-Acetyl Glucosamine) 60 Caps",
                "N-acetyl glucosamine is an amino sugar which consists of amino acids and glucose. N.A.G contains 500mg N-acetyl glucosamine per capsule.\n",
                "Amino Acids",
                "60",
                0,
                "N-Acetyl Glucosamine (Crustaceans), Capsule Shell (HPMC), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate).   ",
                "• Potent bio-available formulation of N-acetyl glucosamine\n" +
                        "• For full allergen information, scroll over label above\n",
                "One capsule taken twice daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_nag"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitasorbfolicacid",
                "Vitasorb® Folic Acid (Liquid Folic Acid) 15ml",
                "Vitasorb® Folic Acid offers a highly absorbable liquid folic acid providing 400mcg per daily intake and formulated to offer efficient utilisation by the body. The Vitasorb® range of products can be taken under the tongue or added to drinks.\n",
                "Vitamins",
                "15ml",
                0,
                "Purified Water, Preservative (Sodium Carbonate), Folic Acid. ",
                "• Readily absorbable liquid folic acid, ideal for those who have difficulty or prefer not to swallow capsules or tablets\n" +
                        "• Can be taken sublingually (under the tongue) for those with gastrointestinal difficulties\n" +
                        "• Suitable for the elderly\n" +
                        "• Folate contributes to maternal tissue growth during pregnancy\n" +
                        "• Folate contributes to normal amino acid synthesis\n" +
                        "• Folate contributes to normal blood formation\n" +
                        "• Folate contributes to normal homocysteine metabolism\n" +
                        "• Folate contributes to normal psychological function\n" +
                        "• Folate contributes to the normal function of the immune system\n" +
                        "• Folate contributes to the reduction of tiredness and fatigue\n" +
                        "• Presented in a simple, hypoallergenic purified water base\n" +
                        "• The Department of Health recommends that women who could become pregnant or who are already pregnant take Folic Acid daily at (400 micrograms [mcg]) before conception and throughout the first 12 weeks of pregnancy\n",
                "Two drops taken daily in water, juice or milk, with food, or taken directly directly under the tongue, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_vitasorbfolicacid"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_ntcomplex",
                "NT Complex - 60 Capsules",
                "Nutrient Support with L-theanine, lemon balm (Cyracos®), chamomile, lavender, magnesium taurate and B-vitamins.\n",
                "Specific Nutrient Complexes",
                "15ml",
                0,
                "Magnesium Taurate, Lemon Balm (Melissa officinalis Aerial Parts as Cyracos®), Capsule Shell (HPMC), L-Theanine, Magnesium Citrate, Chamomile (Matricaria chamomilla Flowers), Lavender (Lavendula angustifolia Herb), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Vitamin B6 (Pyridoxal-5-Phosphate), Thiamine (as Thiamine Mononitrate), Riboflavin, Niacin (as Nicotinamide). Cyracos® is a trademark of Naturex S.A. ",
                "• NT Complex is a unique, high potency combination of L-theanine, lemon balm, chamomile, lavender, magnesium taurate and B-vitamins to support a balanced nervous system\n" +
                        "• A healthy functioning nervous system is dependent on the right balance of neurotransmitters and hormones\n" +
                        "• Magnesium and vitamins B1, B2, B3 and B6 support energy production and nervous system balance\n" +
                        "• Lemon balm, lavender and chamomile support relaxation and healthy sleep\n" +
                        "• NT Complex is in a capsule for for simple, convenient dosing and can be used every day\n" +
                        "• Two per day dosage allows half dose or flexible dosing through the day\n" +
                        "• Vitamin B6 is provided in its P-5-P form for optimum bioavailability\n" +
                        "• Suitable for vegetarians and vegans\n",
                "Two capsules taken daily with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Not suitable for use during pregnancy, planned pregnancy, or breastfeeding\n" +
                        "Not suitable for individuals taking sleeping or mood modifying medication\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_ntcomplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_fos",
                "F.O.S.(Fructooligosaccharide Powder) 250g",
                "Fructooligosaccharides (F.O.S.) is a natural fibre found in raw fruits and vegetables. BioCare® F.O.S. powder is derived from chicory and can be sprinkled on to food or mixed with drinks.\n",
                "Gastrointestinals",
                "250g",
                0,
                "Fructooligosaccharides (Cichorium intybus Root). ",
                "• F.O.S. (Fructooligosaccharides), like inulin, is a natural source of prebiotic fibre. It is naturally sweet but does not elevate blood sugar, making it an ideal alternative to sugar or sweeteners\n" +
                        "• BioCare® F.O.S. powder is dairy free, derived from chicory, and can easily be sprinkled on to food, or added to smoothies\n" +
                        "• F.O.S. powder can be used as a supplement or in cooking\n" +
                        "• Naturally sweet, so can be added to a number of foods\n" +
                        "• F.O.S. provides insignificant amounts of calorific value making it an ideal alternative to sugar or sweeteners\n" +
                        "• F.O.S. does not elevate blood sugar\n" +
                        "• F.O.S. is a natural  fibre that occurs in unprocessed fruits and vegetables\n",
                "One tablespoon (approx 10g) taken daily, or as professionally directed.  Sprinkle on cereal or fresh fruit or blend into yoghurt or fruit juice.\n" +
                        "\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Consumption may initially cause wind and bloating\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_fos"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_glucosaminehydrochloride",
                "Glucosamine Hydrochloride 60 Tablets",
                "Our Glucosamine Hydrochloride provides 750mg of glucosamine as hydrochloride per tablet, along with vitamin C and magnesium.  Added vitamin C contributes to the normal collagen formation for the normal function of bone and cartilage.\n",
                "Amino Acids",
                "60",
                0,
                "Glucosamine Hydrochloride, Bulking Agents (Dicalcium Phosphate & Microcrystalline Cellulose), Vitamin C (as Magnesium Ascorbate), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Acacia Gum, Tablet Coating [Glazing Agents (HPMC & Glycerin) & Colour (Titanium Dioxide)], Sodium Carboxymethyl Cellulose *From the fungus Aspergillus niger.  ",
                "• Tried and tested for WADA (World Anti-Doping Agency) banned substances to ensure that they are safe for professional sports persons to use\n" +
                        "• Vitamin C contributes to the normal collagen  formation for the normal function of  bones, skin, teeth, cartilage and blood vessels\n" +
                        "• Vitamin C contributes to maintaining the normal function of the immune system during and after intense physical exercise\n" +
                        "• Glucosamine 1500mg provided by\n" +
                        "Glucosamine Hydrochloride 1810mg\n",
                "One tablet taken twice daily before food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_glucosaminehydrochloride"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_zincascorbate",
                "Zinc Ascorbate 60 Capsules",
                "Combines zinc and vitamin C to provide a  bioavailable form of this mineral. Zinc is an important mineral that plays a key role in normal function of the immune system, DNA synthesis, healthy bones, cognitive function, fertility and reproduction.\n",
                "Minerals",
                "60",
                0,
                "Bulking Agent (Cellulose), Vitamin C (as Zinc Ascorbate),  Capsule Shell (HPMC), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate).  ",
                "• One a day high potency bio-available zinc supplement\n" +
                        "• Suitable for individuals with malabsorption and poor zinc status\n" +
                        "• Vitamin C contributes to normal energy-yielding metabolism\n" +
                        "• Vitamin C contributes to normal functioning of the nervous system\n" +
                        "• Vitamin C contributes to normal psychological function\n" +
                        "• Vitamin C and zinc contribute to the normal function of the immune system\n" +
                        "• Vitamin C and zinc are antioxidants that contribute to the protection of cells from oxidative stress\n" +
                        "• Vitamin C contributes to the reduction of tiredness and fatigue\n" +
                        "• Vitamin C contributes to the regeneration of the reduced form of vitamin E\n" +
                        "• Vitamin C increases iron absorption\n" +
                        "• Zinc contributes to normal DNA synthesis\n" +
                        "• Zinc contributes to normal acid-base metabolism\n" +
                        "• Zinc contributes to normal carbohydrate metabolism\n" +
                        "• Zinc contributes to normal cognitive function\n" +
                        "• Zinc contributes to normal fertility and reproduction\n" +
                        "• Zinc contributes to normal macronutrient metabolism\n" +
                        "• Zinc contributes to normal metabolism of fatty acids\n" +
                        "• Zinc contributes to normal metabolism of vitamin A\n" +
                        "• Zinc contributes to normal protein synthesis\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_zincascorbate"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_coppergluconate",
                "Copper Gluconate 90 Tablets",
                "Copper Gluconate provides 1.1mg copper per daily intake in tablet form. Copper is known to contribute to the maintenance of normal connective tissues, normal energy metabolism and the  functioning of the nervous and immune systems.\n",
                "Minerals",
                "90",
                0,
                "One tablet taken daily with food or as professionally directed.\n",
                "• A handy, daily supplement\n" +
                        "• Easy to swallow small tablet form\n" +
                        "• Copper may be required by older individuals\n" +
                        "• Copper contributes to maintenance of normal connective tissues\n" +
                        "• Copper contributes to normal energy-yielding metabolism\n" +
                        "• Copper contributes to normal functioning of the nervous and immune systems\n" +
                        "• Copper contributes to normal hair and skin pigmentation\n" +
                        "• Copper contributes to normal iron transport in the body\n" +
                        "• Copper contributes to the protection of cells from oxidative stress\n",
                "One tablet taken daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_coppergluconate"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_folguard",
                "FolGuard® (Folic Acid & B12) 30 Caps",
                "Folguard® provides an ideal dietary supplement which combines folic acid  with vitamin B12 in it's active form hydroxycobalamine. Folic acid and vitamin B12 are nutrients suited to being taken together and have a role in the process of cell division.\n",
                "Vitamins",
                "30",
                0,
                "Bulking Agent (Cellulose), Capsule Shell (HPMC), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Folic Acid, Vitamin B12 (as Hydroxycobalamin), Vitamin B12 (as Cyanocobalamin). ",
                "• Convenient one a day capsule formula\n" +
                        "• Folic acid contributes to maternal tissue growth during pregnancy\n" +
                        "• Vitamin B12 contributes to normal energy-yielding metabolism\n" +
                        "• Vitamin B12 contributes to normal functioning of the nervous system\n" +
                        "• Vitamin B12 and folic acid contribute to normal homocysteine metabolism\n" +
                        "• Vitamin B12 and folic acid contribute to normal psychological function\n" +
                        "• Vitamin B12 contributes to normal red blood cell formation\n" +
                        "• Vitamin B12 and folic acid contribute to the normal function of the immune system\n" +
                        "• Vitamin B12 and folic acid contribute to the reduction of tiredness and fatigue\n" +
                        "• Vitamin B12 and folic acid have a role in the process of cell division\n" +
                        "• Folic acid contributes to normal amino acid synthesis\n" +
                        "• Folic acid contributes to normal blood formation\n" +
                        "• Vitamin B12 and folic acid work synergistically\n" +
                        "• The Department of Health recommends that women who could become pregnant or who are already pregnant take Folic Acid daily at (400 micrograms [µg]) before conception and throughout the first 12 weeks of pregnancy\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_folguard"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_potassiumcitrate",
                "Potassium Citrate 90 Capsules",
                "Potassium Citrate offers 100mg potassium in citrate form which means it can be absorbed with very little stomach acid. This capsule can be opened and is suitable for vegans and vegetarians.\n",
                "Minerals",
                "90",
                0,
                "Potassium Citrate, Bulking Agent (Cellulose), Capsule Shell (HPMC) , Anti-Caking Agent (Magnesium Stearate). ",
                "• 100mg potassium per capsule\n" +
                        "• Potassium presented in the citrate form\n" +
                        "• Citric acid acts as a mineral transporter\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_potassiumcitrate"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_phytosterolcomplex",
                "Phytosterol Complex 90 Capsules",
                "Phytosterol Complex is a combination of botanical extracts, containing hops, alfalfa, licorice, sage and celery seed and is suitable for women during and after child bearing age.\n",
                "Herbal",
                "90",
                0,
                "Sage (Salvia officinalis Leaf), Hops (Humulus lupulus Flower), Capsule Shell (Hydroxypropyl Methylcellulose), Licorice (Glycrrhizia glabra Root), Alfalfa (Medicago sativa Herb), Bulking Agent (Cellulose), Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide), Celery Seed (Apium graveolens Seed) (Celery).  ",
                "• Hops help to maintain a calm and comfortable menopause, helping signs such as hot flushes, sweating, restlessness and irritability\n" +
                        "• Sage promotes hormonal balance of women and stimulates the pituitary and thyroid\n" +
                        "• Hypoallergenic formulation in a capsule for easy swallowing\n" +
                        "• Suitable for women during and after child bearing age\n" +
                        "• For full allergen information, scroll over label above\n",
                "One capsule taken three times daily with food or as professionally directed.\n",
                "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_phytosterolcomplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_linseed_oil_1000",
                "Linseed Oil 1000 60 Capsules",
                "Linseed oil is a natural source of omega-3 fatty acids. Derived from cold pressed linseeds, our Linseed Oil 1000 also contains vitamin E for stability and as an antioxidant it contributes to the protection of cells from oxidative stress.\n",
                "Fatty Acids",
                "60 & 90",
                0,
                "Linseed Oil, Capsule Shell (Modified Potato Starch, Glycerol, Sorbitol, Gelling Agent [Eucheuma Seaweed], Emulsifier [Mono & Diglycerides], Glazing Agent [Carnauba Wax], Medium Chain Triglycerides), Vitamin E (As DL-Alpha Tocopheryl Acetate).  ",
                "• Linseed oil is one of the richest, natural sources of omega-3 fatty acids and contains approximately twice as much Omega-3 fatty acids per gram of weight as fish oil\n" +
                        "• Linseed oil (flaxseed oil) contains 58% omega-3 and 14% omega-6 fatty acids\n" +
                        "• Contains vitamin E to maintain stability\n" +
                        "• Vitamin E is an antioxidant that contributes to the protection of cells from oxidative stress\n" +
                        "• Lignans present in linseed oil may have oestrogenic properties and therefore may offer benefits for menopausal health\n",
                "One capsule taken three times daily with food or as professionally directed.\n",
                "Caution is advised with anti-coagulant medication\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_linseed_oil_1000"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_repleteintensive",
                "Replete Intensive 7 Sachets",
                "Replete Intensive is a professional potency product that contains the LAB4 complex of live probiotic cells and delivers 130 billion viable cells per sachet.\n",
                "Probiotics",
                "7",
                0,
                "Fructooligosaccharides (F.O.S.), Potato Maltodextrin, Apricot Powder, Lactobacillus acidophilus, Lactobacillus salivarius, Bifidobacterium bifidum and Bifidobacterium lactis. ",
                "• Contains the unique, extensively tested LAB<sup>4</sup> complex of friendly bacteria with the added benefit of Lactobacillus salivarius\n" +
                        "• A unique formula providing live bacteria in a base of freeze dried apricots\n" +
                        "• Contains Fructooligosaccharides (F.O.S.) derived from chicory\n" +
                        "• Acid stable and bile tolerant organisms of human compatible origin\n" +
                        "• Dairy-free\n" +
                        "• Powder form in sachets for convenience and to be easy to consume in food or liquid\n" +
                        "• Guaranteed 130 billion potency per daily intake to end of shelf life if stored correctly\n",
                "One sachet dissolved in water and taken before breakfast for 2-7 days, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 4&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_repleteintensive"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitamin_c1000",
                "Vitamin C 1000 30 Tablets",
                "Vitamin C 1000 provides 803mg of citrus-free magnesium ascorbate (a low-acid form of vitamin C) with the bioflavonoids bilberry and grapeseed extract.\n",
                "Vitamins",
                "30 & 60 & 90",
                0,
                "Vitamin C (as Magnesium Ascorbate), Bulking Agents (Dicalcium Phosphate & Microcrystalline Cellulose), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Acacia Gum, Glazing Agents (Sodium Carboxymethylcellulose, Dextrin, Dextrose, Soya Lecithin & Sodium Citrate), Potato Maltodextrin, Bilberry Powder (Vaccinium myrtillus Fruit), Grapeseed Extract (Vitus vinifera Seed as Vitaflavan®), Stabiliser (Sodium Carboxymethylcellulose), Citric Acid.",
                "• Convenient 1 a day tablet\n" +
                        "• Provides a readily absorbable form of vitamin C and magnesium\n" +
                        "• Buffered, low acidity form of vitamin C to reduce stomach irritation\n" +
                        "• Suitable for individuals requiring a citrus-free vitamin C supplement\n" +
                        "• Tried and tested for WADA (World Anti-Doping Agency) banned substances to ensure that they are safe for professional sports persons to use\n" +
                        "• Vitamin C contributes to maintain the normal function of the immune system during and after intense physical exercise\n" +
                        "• Vitamin C contributes to normal collagen formation for the normal function of blood vessels, bones, gums, skin teeth & cartilage\n" +
                        "• Vitamin C and magnesium contribute to normal psychological function\n" +
                        "• Vitamin C contributes to the protection of cells from oxidative stress\n" +
                        "• Magnesium contributes to a reduction of tiredness and fatigue\n" +
                        "• Magnesium contributes to normal muscle function\n" +
                        "• Bilberry anthocyanosides can help capillary vessels health and elasticity of veins against harmful effect of free radicals \n" +
                        "• For full allergen information, scroll over label above\n",
                "One tablet taken daily with food or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_vitamin_c1000"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_garciniacambogiaplus",
                "Garcinia Cambogia Plus 90 Capsules",
                "Garcinia Cambogia Plus contains garcinia cambogia (a natural source of hydroxycitric acid) with manganese, chromium, vitamins B5 and C. \n",
                "Herbal",
                "90",
                0,
                "Garcinia cambogia rind, Capsule Shell (HPMC), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Vitamin B5 (as Calcium Pantothenate), Vitamin C (as Ascorbic Acid), Manganese Gluconate, Chromium Picolinate.  ",
                "• Pantothenic acid (vitamin B5), manganese & vitamin C contribute to normal energy-yielding  metabolism and contribute to a reduction in tiredness and fatigue\n" +
                        "• Vitamin C contributes to maintain the normal function of the immune system during and after intense physical exercise\n" +
                        "• Vitamin C & manganese contribute to the protection of cells from oxidative stress\n" +
                        "• Chromium contributes to normal macronutrient metabolism & maintenance of normal blood glucose levels\n" +
                        "• Garcinia cambogia contributes to weight management\n",
                "One capsule taken three times daily, 20 minutes before food, or as professionally directed.\n",
                "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_garciniacambogiaplus"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_broadspectrumaminoacids",
                "Broad Spectrum Amino Acids 60 Capsules",
                "Broad Spectrum Amino Acids includes a wide range of key amino acids in a single capsule which can be opened for ease of use. Ideal for individuals following a low protein diet or during convalescence.\n",
                "Amino Acids",
                "60 & 90",
                0,
                "Capsule Shell (HPMC), L- Glutamic Acid, L-Aspartic Acid, L-Leucine, L-Lysine Hydrochloride, L-Serine, L-Valine, L-Arginine Hydrochloride, L-Isoleucine, L-Alanine, L-Phenylalanine, L-Threonine, L-Tyrosine, L-Proline, L-Methionine, L-Glycine, L-Histidine, L-Cysteine Hydrochloride, Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate). ",
                "• Ideal for individuals following inadequate dietary regimes\n" +
                        "• Can be used in convalescence\n" +
                        "• May be used during periods of extreme physical activity\n",
                "One capsule taken three times daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_broadspectrumaminoacids"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_methylpregnancymultinutrient",
                "Methyl Pregnancy Multinutrient - 60 Capsules",
                "Methyl Pregnancy Multinutrient is a superior, high potency formulation to support pregnancy and breastfeeding, containing active folic acid in the form of Methylfolate.\n\n",
                "Amino Acids",
                "60",
                0,
                "Vitamin C (as Magnesium Ascorbate), Calcium Citrate, Capsule Shell (HPMC), Magnesium Citrate, Choline Bitartrate, Ferrous Citrate, Vitamin B6 (as Pyridoxal-5-Phosphate), Spirulina (Athrospira platensis Algae), Zinc Citrate, Potato Maltodextrin, Vitamin E (as D-Alpha Tocopheryl Succinate & Natural Mixed Tocotrienols & Tocopherols), Sodium Alginate, Dicalcium Phosphate, Niacin (as Nicotinamide), Acacia Gum, Beetroot Extract (Beta vulgaris Root), Modified Corn Starch, Natural Mixed Carotenoids, Vitamin D3 (preparation as Cholecalciferol (Lichen) with Tapioca Maltodextrin, Corn Starch, Coconut Oil, Sucrose, Silicon Dioxide, D-Alpha Tocopherol, Ascorbyl Palmitate), Anti-Caking Agent (Silicon Dioxide), Sucrose, Microcrystalline Cellulose, Copper Gluconate, Pantothenic Acid (as Calcium Pantothenate), Mono/Di and Triglycerides, Corn Starch, Pea Starch, Sodium Borate, HPC, Corn Maltodextrin, Corn Dextrin, Sorbitan Stearate, Thiamine (as Thiamine Mononitrate), Riboflavin, Antioxidants (Ascorbyl Palmitate, DL-Alpha Tocopherol & Mixed Tocopherols), Vitamin K2 (as Menaquinone-7), Manganese Gluconate, Tricalcium Phosphate, Folic Acid (as Quatrefolic® [(6S)-5-methyltetrahydrofolic acid glucosamine salt]), Medium Chain Triglycerides, Vitamin A (as Retinyl Acetate), Citric Acid, Potassium Iodide, Chromium Picolinate, L-Selenomethionine, Sodium Molybdate, Vitamin B12 (as Methylcobalamin), Rosemary Extract, Biotin, Vitamin K1 (as Phylloquinone). ",
                "• Taking a supplement containing 400mcg of folic acid is recommended prior to and during early pregnancy as it contributes to normal maternal tissue growth\n" +
                        "• Folate (folic acid) is provided in the '5-MTHF' (Methylfolate) form at current Government recommended levels for pregnancy (400mcg per two capsules)\n" +
                        "• 5-MTHF is the natural form of folate found in food such as leafy green vegetables and is already active and ready for the body to use\n" +
                        "• Selenium and zinc contribute to normal fertility and reproduction\n" +
                        "• Provides optimum levels of vitamin D3 derived from lichen, so suitable for vegans and vegetarians\n" +
                        "• B vitamins are required for the release of energy from food, for the healthy function of the nervous system and for the production of hormones. Vitamin B2, B3, B5, B6 and B12 contribute to the reduction of tiredness and fatigue\n" +
                        "• Vitamin B6, B12, D, C, zinc, iron, selenium and folate contribute to the normal function of the immune system\n",
                "Two capsule taken daily with food or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Long term intakes of amounts greater than 10mg vitamin B6 daily may lead to mild tingling and numbness\n" +
                        "This product contains iron, which if taken in excess, may be harmful to young children. Keep out of sight and reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_methylpregnancymultinutrient"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_eyecareplus",
                "EyeCare Plus 60 Capsules",
                "EyeCare Plus is an innovative blend of vitamin A, minerals, bilberry, ginkgo biloba, lutein & grapeseed extracts in a capsule. Also includes zinc, astaxanthin & zeaxanthin. Vitamin A, bilberry and lutein contribute to the maintenance of normal vision\n",
                "Specific Nutrient Complexes",
                "60",
                0,
                "Bulking Agent (Cellulose), Capsule Shell (HPMC & Colours (Red Iron Oxide & Titanium Dioxide)), Bilberry Extract (Vaccinium myrtillus Berry), Vitamin C (as Potassium Ascorbate), Vitamin E (as D-Alpha Tocopheryl Acetate), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Ginkgo Biloba Extract (Ginkgo biloba Leaf), Grapeseed Extract (Vitus vinifera Seed as Vitaflavan®), Acacia Gum, Modified Corn Starch, Sodium Alginate, Zinc Citrate, Modified Tapioca Starch, Soy Protein Isolate, Olive Oil, Lutein (Tagetes erecta Flowers), Soya Oil, Corn Maltodextrin, Antioxidants (Natural Mixed Tocopherols, Ascorbyl Palmitate, Ascorbic Acid & DL-Alpha Tocopherol), Hydroxypropyl Methylcellulose, Beta Carotene, Pea Starch, Manganese Citrate, Astaxanthin (Haematococcus pluvialis Algae), Lycopene, Chromium Picolinate, Sunflower Oil, Vitamin A (as Retinyl Palmitate), Zeaxanthin (Lycium chinensis Berry), Sodium Selenite, Rosemary Extract.  ",
                "• A unique blend of vitamins and minerals with botanical extracts\n" +
                        "• EyeCare Plus can also be used as a broad spectrum antioxidant combination \n" +
                        "• Vitamin C, A & selenium contribute to the normal function of the immune system\n" +
                        "• Vitaflavan® is a standardised, high potency, premium quality grapeseed extract which is an excellent source of purified, bio-available proanthocyanidins\n" +
                        "• Chromium contributes to normal macronutrient metabolism and contributes to the maintenance of normal blood glucose levels\n" +
                        "• Vitamin A contributes to the maintenance of normal vision\n" +
                        "• Antioxidant anthocyanosides in bilberry help maintain the proper retina functions\n" +
                        "• Lutein helps maintain normal vision\n" +
                        "• For full allergen information, scroll over label above\n",
                "One capsule taken twice daily with food, or as professionally directed.\n",
                "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_eyecareplus"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_acetylcarnitinealphalipoicacid",
                "Acetyl Carnitine & Alpha Lipoic Acid 30 Capsules",
                "Our Acetyl Carnitine & Alpha Lipoic Acid formulation provides 300mg N-acetyl carnitine and 200mg alpha lipoic acid in a vegetarian and vegan friendly capsule.\n",
                "Specific Nutrient Complexes",
                "30",
                0,
                "N-Acetyl Carnitine Hydrochloride, Alpha Lipoic Acid,  Capsule Shell (HPMC), Bulking Agent (Cellulose), Anti-Caking Agent (Silicon Dioxide & Magnesium Stearate). ",
                "• Combination of acetyl carnitine and alpha lipoic acid\n" +
                        "• Acetyl-l-carnitine is the preferred, highly bioavailable form of carnitine\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_acetylcarnitinealphalipoicacid"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_spectrumzyme",
                "Spectrumzyme (Enzyme Complex) 90 Caps",
                "Spectrumzyme is a broad spectrum enzyme complex which includes bromelain, lactase, papain, protease, lipase, gluten protease and amylase.  Presented in capsules this formulation is acid stable against stomach acid pH.\n",
                "Digestive Enzymes",
                "90",
                0,
                "Potato Maltodextrin, Bulking Agent (Cellulose), Bromelain, Capsule Shell, (HPMC), Papain (Sulphites), Lactase, Anti-Caking Agent (Magnesium Stearate), Protease, Lipase, Gluten Protease, Amylase. ",
                "• High potency, broad spectrum enzyme complex\n" +
                        "• All enzymes are from vegetable sources\n" +
                        "• Acid stable against stomach pH and able to retain digestive capability over a wide range of pH variances\n",
                "One capsule taken with each main meal (max 3 capsules daily), or as professionally directed.\n",
                "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_spectrumzyme"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_omegacare",
                "OmegaCare (Liquid Fish Oil with Orange) 225ml",
                "OmegaCare is  a potent and highly concentrated fish oil derived from anchovies and sardines, flavoured with natural orange oil. This great tasting liquid can be taken neat or mixed with water or juice and provides EPA and DHA.\n",
                "Fatty Acids",
                "225ml",
                0,
                "Fish Oil, Antioxidant (Natural Mixed Tocopherols), Sweet Orange Oil (Citrus sinensis Peel), Sunflower Oil.",
                "• Flavoured with natural essential oil of orange for a great taste\n" +
                        "• Suitable for use during pregnancy\n" +
                        "• Liquid format so ideal for those that have difficulty or prefer not to swallow capsules\n" +
                        "• Very high potency liquid yielding over 1g of EPA per teaspoon\n" +
                        "• Simple formulation of pure fish oil concentrate flavoured with essential oil of orange for a great taste\n" +
                        "• Provides fish oil from anchovies and sardines and is free from detectable PCBs and contaminants\n" +
                        "• Utilises our 'Multox' antioxidant system for enhanced stability\n" +
                        "• Naturally concentrated using our patented NEO-3™ process, utilising lipase enzymes\n" +
                        "• Naturally pre-digested using our patented NEO-3™ process, for improved absorption\n" +
                        "• DHA contributes to maintenance of normal brain function\n" +
                        "• DHA contributes to the maintenance of normal vision\n" +
                        "• EPA and DHA contribute to the normal function of the heart\n" +
                        "• Maternal intake of Docosahexaenoic acid (DHA) contributes to the normal brain and eye development of the foetus and breastfed infants\n" +
                        "• For full allergen information, scroll over label above\n",
                "One (5ml) to four (20ml) teaspoons taken daily with food, or as professionally directed. Shake well before use and refrigerate after opening.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Caution is advised with anti-coagulant medication\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Vacuum sealed for stability\n" +
                        "Once opened, refrigerate and consume within 4 months\n",
                ImageRetriever.createUrl("biocare_omegacare"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_biomulsion_omegafruits",
                "BioMulsion® OmegaFruits (Omega-3 & Fruit) 300ml",
                "BioMulsion® OmegaFruits is an emulsified fish oil  derived from sardines and anchovies, with tropical fruit concentrates, making it an optimally absorbed and great tasting source of essential fatty acids.\n",
                "Fatty Acids",
                "300ml",
                0,
                "Pineapple Juice Concentrate, Fish Oil Concentrate, Fructose, Water, Modified Corn Starch, Mango Puree, Orange Juice Concentrate, Banana Puree, Pineapple Flavour, Acacia Gum, Antioxidant (Natural Mixed Tocopherols & Ascorbic Acid), Vanilla Flavour, Sunflower Oil, Xanthan Gum, Preservative (Potassium Sorbate), Citric Acid. ",
                "• Provides fish oil from south pacific anchovies and sardines which are further down in the food chain than traditional sources of fish oils\n" +
                        "• Each batch is independently tested for heavy metals, PCBs and other organic and inorganic contaminants\n" +
                        "• OmegaFruits®  is vacuum sealed to maintain maximum quality and stability\n" +
                        "• DHA contributes to the maintenance of normal brain function\n" +
                        "• DHA contributes to the maintenance of normal vision\n" +
                        "• EPA and DHA contribute to the normal function of the heart\n" +
                        "• Maternal intake of Docosahexaenoic acid (DHA) contributes to the normal brain and eye development of the foetus and breastfed infants\n" +
                        "• Naturally concentrated and pre-digested using our patented NEO-3™ process, utilising lipase enzymes\n" +
                        "• Emulsified using our unique BioMulsion® process, dramatically increasing the bio-availability of the oils\n" +
                        "• Utilises our 'Multox' antioxidant system for enhanced stability\n" +
                        "• For full allergen information, scroll over label above\n",
                "One teaspoon (approx. 5ml) taken twice daily with food, or as professionally directed. Shake well before use.\n" +
                        "Can be mixed into water or juice, or taken directly from the spoon.\n",
                "Caution is advised with anti-coagulant medication\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Vacuum sealed for stability\n" +
                        "Once opened, refrigerate and consume within 4 months\n",
                ImageRetriever.createUrl("biocare_biomulsion_omegafruits"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_biomulsionjointguard",
                "BioMulsion JointGuard® (Omega-3 & Glucosamine) 300",
                "BioMulsion® JointGuard is a unique combination of pure fish oil concentrate from anchovies blended with glucosamine hydrochloride, vitamin E and ginger resulting in a smooth textured emulsion with a great taste.\n",
                "Fatty Acids",
                "300ml",
                0,
                "Concentrated Pineapple Juice, Fish Oil Concentrate, Fructose, Modified Corn Starch, Water, Glucosamine Hydrochloride, Concentrated Orange Juice, Mango Puree, Ascorbic Acid, Pineapple Flavour, Banana Puree, Acacia Gum, Antioxidant (Natural Mixed Tocopherols), Vanilla Flavour, Sunflower Oil, Alginate, Preservative (Potassium Sorbate), Ginger Oil (Zingiber officinalis), Citric Acid. ",
                "• Liquid form is ideal for those who are unable or prefer not to swallow capsules or tablets\n" +
                        "• Glucosamine hydrochloride is the preferred alternative to the sulphate form and delivers a higher concentration of glucosamine\n" +
                        "• Provides fish oil from anchovies and sardines and is free from detectable PCBs and contaminants\n" +
                        "• DHA contributes to maintenance of normal brain function\n" +
                        "• DHA contributes to the maintenance of normal vision    \n" +
                        "• EPA and DHA contribute to the normal function of the heart\n" +
                        "• BioMulsion® JointGuard utilises our 'Multox' antioxidant system for enhanced stability\n" +
                        "• BioMulsion® JointGuard is naturally concentrated and pre-digested using our patented NEO-3™ process, utilising lipase enzymes\n" +
                        "• BioMulsion® JointGuard is emulsified for optimal absorption\n" +
                        "• For full allergen information, scroll over label above\n",
                "One tablespoon (15ml) taken daily with food or as professionally directed\n",
                "Caution is advised with anti-coagulant medication\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Vacuum sealed for stability\n" +
                        "Once opened, refrigerate and consume within 4 months\n",
                ImageRetriever.createUrl("biocare_biomulsionjointguard"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_biomulsion_megaplex",
                "BioMulsion® OmegaPlex (Omega-3 & 6) 300ml",
                "BioMulsion® OmegaPlex provides a balanced source of omega-3, 6 & 9 fatty acids in a great tasting emulsified berry base.  Emulsified using our unique BioCare®  BioMulsion® process, dramatically increases the bioavailability of the oils.\n",
                "Fatty Acids",
                "300ml",
                0,
                "Pineapple Juice Concentrate, Fish Oil Concentrate, Fructose, Water, Elderberry Juice Concentrate, Modified Corn Starch, Mango Puree, Concentrated Orange Juice, Borage Oil (Borago officinalis Seed), Linseed Oil (Linum sp. Seed), Banana Puree, Pineapple Flavour, Acacia Gum, Antioxidant (Natural Mixed Tocopherols), Sunflower Oil, Vanilla Flavour, Xanthan Gum, Preservative (Potassium Sorbate), Citric Acid, Ascorbic Acid. ",
                "• Provides fish oil from anchovies and sardines and is free from detectable PCBs and contaminants\n" +
                        "• Liquid form that is ideal for those that have difficulty or prefer not to swallow capsules\n" +
                        "• The fish oil is naturally concentrated and pre-digested using our patented NEO-3™ process, utilising lipase enzymes\n" +
                        "• Emulsified using our unique BioMulsion® process, dramatically increasing the bio-availability of the oils\n" +
                        "• Utilises our 'Multox' antioxidant system for enhanced stability\n" +
                        "• DHA contributes to maintenance of normal brain function\n" +
                        "• DHA contributes to the maintenance of normal vision    \n" +
                        "• EPA and DHA contribute to the normal function of the heart\n" +
                        "• Maternal intake of Docosahexaenoic acid (DHA) contributes to the normal brain and eye development of the foetus and breastfed infants\n" +
                        "• ALA contributes to the maintenance of normal blood cholesterol levels\n" +
                        "• For full allergen information, scroll over label above\n",
                "One teaspoon (approximately 5ml) taken twice daily with food, or as professionally directed.  Shake well before use.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Caution is advised with anticoagulant medication\n" +
                        "Not suitable for individuals suffering from epilepsy\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Vacuum sealed for stability\n" +
                        "Once opened, refrigerate and consume within 4 months\n",
                ImageRetriever.createUrl("biocare_biomulsion_megaplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_mitoguardintensive",
                "MitoGuard® Intensive Single Sachet",
                "MitoGuard® Intensive is a unique, high potency powder combination designed to support energy production and reduce fatigue.  It provides a synergistic blend of d-ribose, CoQ10, vitamins B2 and B3, N-acetyl cysteine and magnesium malate.\n",
                "Specific Nutrient Complexes",
                "1 & 14",
                0,
                "Magnesium Malate, Fructooligosaccharides (Cichorium intybus Root), D-Ribose, Potato Maltodextrin, Fructose, Corn  Maltodextrin, Niacin (as Nicotinamide), Corn Starch, Bilberry Extract (Vaccinium myrtillus Berry), Blackberry Extract (Rubus fruticosus Fruit), Preservative (Citric Acid), Riboflavin (as Riboflavin-5-Phosphate Sodium), Sweet Cherry Extract (Prunus avium Fruit), N-Acetyl Carnitine Hydrochloride, Olive Oil,  Beetroot Extract (Beta vulgaris Root), N-Acetyl Cysteine, Acacia Gum, Natural Blackcurrant Flavour, Coenzyme Q10 Powder, Citric Acid, Antioxidant (Natural Mixed Tocopherols), Sunflower Oil. ",
                "• Magnesium, vitamin B2 and B3 are essential components of energy production and contribute to the reduction of tiredness and fatigue\n" +
                        "• Designed to suit individual needs - can be used alone or in combination with other products in the range\n" +
                        "• MitoGuard® Intensive can be easily mixed into liquids\n",
                "One sachet mixed in water, juice or milk and taken daily with food or as professionally directed.\n",
                "Not suitable for use during planned pregnancy, pregnancy, or breastfeeding.\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "If you are under medical supervision, please consult a doctor before use.\n" +
                        "Store below 25oC in a dry place away from direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_mitoguardintensive"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_th_intensive",
                "TH Intensive Single Sachet",
                "TH Intensive is a unique, high potency powder combination containing iodine and selenium, which support normal thyroid function. It contains high potency amino acids, nutrients and antioxidants in a single, great tasting and convenient sachet.\n",
                "Specific Nutrient Complexes",
                "1 & 14",
                0,
                "Fructooligosaccharides (Chicorium intybus Root), L-Tyrosine, Potato Maltodextrin, Fructose, Maize Maltodextrin, Vitamin C (as Magnesium Ascorbate), Blackberry Extract (Rubus fruticosus Fruit), Bilberry Extract (Vaccinium myrtillus Berry), Preservative (Citric Acid), Sweet Cherry Extract (Prunus avium Fruit), L-Glycine, Acacia Gum, Niacin (as Nicotinamide), Zinc Citrate, Natural Blackcurrant Flavour, Dicalcium Phosphate, Citric Acid, Thiamine (as Thiamine Hydrochloride), Microcrystalline Cellulose, Potassium Iodide, L-Selenomethionine. ",
                "• Iodine and selenium support normal thyroid function\n" +
                        "• Vitamin C, zinc and selenium are antioxidants which support the protection of cells from oxidative stress\n" +
                        "• Vitamins C, B3 and B1 support energy production and contribute to the reduction of tiredness and fatigue\n" +
                        "• Designed to suit individual needs - can be used alone or in combination with other products in the range\n" +
                        "• TH Intensive can easily be mixed into liquids\n",
                "One sachet mixed in water, juice or milk and taken daily with food or as professionally directed.\n",
                "Not suitable for use during pregnancy, planned pregnancy or breastfeeding.\n" +
                        "Do not use alongside any thyroid medication unless under the supervision of a medical practitioner.\n" +
                        "Caution is advised in cases of melanoma, mania or high blood pressure.\n" +
                        "Not to be taken in conjunction with MAO inhibitor drugs.\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "If you are under medical supervision, please consult a doctor before use.\n" +
                        "Store below 25oC in a dry place away from direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_th_intensive"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_adintensive",
                "AD Intensive Single Sachet",
                "AD Intensive is a unique powder combination to support energy levels.  It contains a high potency of amino acids, botanicals, nutrients and antioxidants in a single, great tasting and convenient sachet.",
                "Specific Nutrient Complexes",
                "1 & 14",
                0,
                "Potato Maltodextrin, L-Citrulline, Licorice (Glycyrrhizia glabra Root), Fructose, Magnesium Malate, L-Alanine, Korean Ginseng (Panax ginseng Root), Bilberry Extract (Vaccinium myrtillus Berry), Blackberry Extract (Rubus fruticosus Fruit), Sweet Cherry Extract (Prunus avium Fruit), Vitamin C (as Magnesium Ascorbate), Pantothenic Acid (as Calcium Pantothenate), Corn Maltodextrin, Fructooligosaccharides (Chicorium intybus Root), Niacin (as Nicotinamide), Acacia Gum, Citric Acid, Natural Blackcurrant Flavour, Cinnamon Extract (Cinnamomum zeylanicum/cassia Bark), Chromium Picolinate. ",
                "• Vitamin C is an antioxidant which supports the protection of cells from oxidative stress\n" +
                        "• Vitamins C, B3, B5 and magnesium support energy production and contribute to the reduction of tiredness and fatigue\n" +
                        "• Chromium supports normal blood glucose levels\n" +
                        "• Designed to suit individual needs - can be used alone or in combination with other products in the range\n" +
                        "• AD Intensive can be easily mixed into liquid\n",
                "One sachet mixed in water, juice or milk and taken daily with food or as professionally directed.\n",
                "Not suitable for use during planned pregnancy, pregnancy, or breastfeeding\n" +
                        "Caution advised for individuals with high blood pressure\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_adintensive"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_performintensive",
                "Perform Intensive Single Sachet",
                "Perform Intensive is a unique, high potency powder combination designed to support energy production. It contains vitamin B3, arginine, citrulline, glutamine and beetroot extract in a single, great tasting and convenient sachet.\n",
                "Specific Nutrient Complexes",
                "1 & 14",
                0,
                "L-Arginine Hydrochloride, Beetroot Powder (Beta vulgaris Root), Beetroot Juice, L-Citrulline, Potato Maltodextrin, Fructose, L-Glutamine, Corn Maltodextrin, Bilberry Extract (Vaccinium myrtillus Berry), Blackberry Extract (Rubus fruticosus Fruit), Sweet Cherry Extract (Prunus avium Fruit), Niacin (as Nicotinamide & Nicotinic Acid), Acacia Gum, Natural Blackcurrant Flavour, Citric Acid, Antioxidant (Ascorbic Acid), Silicon Dioxide. /cassia Bark), Chromium Picolinate. ",
                "• Vitamin B3 supports energy production and contributes to the reduction of tiredness and fatigue.\n" +
                        "• Contains a unique standardised beetroot extract providing nitrates\n" +
                        "• Designed to suit individual needs - can be used alone or in combination with other products in the range.\n" +
                        "• Perform Intensive can easily be mixed into liquids.\n",
                "One sachet mixed in water, juice or milk and taken daily with food or as professionally directed.\n",
                "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_performintensive"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitamincrosehip",
                "Vitamin C Rosehip Complex - 150g Powder",
                "NEW Vitamin C Rosehip Complex is a great tasting vitamin C powder with minerals, bioflavonoids and berry extracts. This handy powder dissolves easily into water. Each 5g dose provides 1g of vitamin C with minerals such as zinc and manganese.",
                "Specific Nutrient Complexes",
                "150g",
                0,
                "Sweeteners (Xylitol & Steviol Glycosides), Vitamin C (as Ascorbic Acid), Potato Maltodextrin, Magnesium Ascorbate, Apricot Powder (Prunus armeniaca Fruit), Blackberry Extract (Rubus fruticosus Fruit), Rosehip Powder (Rosa canina Hips), Calcium Ascorbate, Zinc Ascorbate, Potassium Ascorbate, Sweet Cherry Extract (Prunus avium Fruit), Citrus Bioflavonoids Complex, Rutin, Bilberry Extract (Vaccinium myrtillus Fruit), Manganese Ascorbate, Citric Acid. ",
                "• Provides a readily absorbable form of vitamin C together with bioavailable forms of zinc and manganese\n" +
                        "• Powder form that is suitable for people who have difficulty or prefer not to swallow capsules or tablets\n" +
                        "• Buffered, low acid form of vitamin C to reduce stomach irritation\n" +
                        "• Vitamin C contributes to normal collagen formation for the normal function of skin\n" +
                        "• Vitamin C contributes to normal functioning of the nervous system\n" +
                        "• Vitamin C and Zinc contribute to the normal function of the immune system\n" +
                        "• Vitamin C, manganese and Zinc contribute to the protection of cells from oxidative stress\n",
                "One rounded scoop (5g) taken daily (mixed into liquid)  with food or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Excessive consumption may produce laxative effects\n" +
                        "This product contains a silica gel bag to absorb moisture\n",
                ImageRetriever.createUrl("biocare_vitamincrosehip"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_nutrisorbliquidmethylbcomplex",
                "Nutrisorb® Liquid Methyl B Complex 15ml",
                "Nutrisorb®  Liquid Methyl B Complex provides the metabolically active forms of B vitamins including vitamin B12 (as methylcobalamin) and folate (as methylfolate).\n",
                "Specific Nutrient Complexes",
                "15ML",
                0,
                "Purified Water, Thiamine (as Thiamine Hydrochloride), Vitamin B6 (as Pyridoxine Hydrochloride), Niacin (as Nicotinamide), Pantothenic Acid (as Calcium Pantothenate), Sodium Bicarbonate, Riboflavin (as Riboflavin-5-Phosphate Sodium), Sodium Carbonate, Folate (as Quatrefolic® [(6S)-5-methyltetrahydrofolic acid glucosamine salt]), Vitamin B12 (as Methylcobalamin).  ",
                "• Methyl B Complex is a superior B complex, providing nutrients in their naturally active forms\n" +
                        "• Methyl B Complex contains folate as methylfolate or 5-MTHF. 5-MTHF is the natural form of folate found in food such as leafy green vegetables and is already active and ready for the body to use\n" +
                        "• We use Quatrefolic®, the most advanced form of folate supplementation available, with optimum stability and bioavailability\n" +
                        "• B vitamins are required for the release of energy from food, for the healthy function of the nervous system and for the production of hormones\n" +
                        "• B vitamins support the circulatory and immune systems and help maintain the health of skin, hair and eyes\n" +
                        "• Vitamin B2, B3, B5, B6 and B12 contributes to the reduction of tiredness and fatigue\n" +
                        "• Vitamin B Complex liquid in a simple, hypoallergenic purified water base\n",
                "Twelve drops taken daily in water with food, or directly under the tongue, or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Do not purchase if the seal is broken\n",
                ImageRetriever.createUrl("biocare_nutrisorbliquidmethylbcomplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_nutrisorb_liquid_vitamin_e",
                "Nutrisorb® Liquid Vitamin E - 25ml",
                "Our Nutrisorb Liquid E is a mix of natural tocotrienols and tocopherols and provides 100iu natural Vitamin E per day.\n",
                "Specific Nutrient Complexes",
                "25ML",
                0,
                "Medium Chain Triglycerides, Natural Mixed Tocotrienols (from Elaeis guineensis Fruit), Vitamin E as (D-Alpha Tocopheryl Acetate & D-Alpha Tocopherol from Elaeis guineensis Fruit).  ",
                "• Vitamin E is an antioxidant that contributes to the protection of cells from oxidative stress.\n" +
                        "• Because it's a liquid, it's flexible. You can combine it with other nutrients and take exactly what you need.\n" +
                        "• It's perfect for individuals who have difficulty swallowing tablets or capsules, such as children or the elderly, or people with digestive and absorption difficulties.\n" +
                        "• 25 days' supply at 1 ml per day\n",
                "1ml taken daily in water with food, or directly under the tongue, or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "If you are under medical supervision, please consult a doctor before use.\n" +
                        "Store below 25°C in a dry place away from direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_nutrisorb_liquid_vitamin_e"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_nutrisorb_liquid_vitamin_e",
                "Nutrisorb® Liquid Methyl B12 15ml",
                "Our Nutrisorb Liquid E is a mix of natural tocotrienols and tocopherols and provides 100iu natural Vitamin E per day.\n",
                "Specific Nutrient Complexes",
                "15ml",
                0,
                "Purified Water, Vitamin B12 (as Methylcobalamin and Adenosylcobalamin), Citric Acid, Preservative (Potassium Sorbate). ",
                "• Nutrisorb® liquids are a" +
                        "range of high potency " +
                        "liquid nutrients\n" +
                        "• Vitamin B12 contributes to normal blood formation, normal homocysteine metabolism, normal psychological function and helps reduce tiredness and fatigue\n" +
                        "• Vitamin B12 contributes to normal red blood cell formation\n" +
                        "• Vitamin B12 contributes to normal cell division\n" +
                        "• Vitamin B12 contributes to normal energy metabolism\n",
                "1ml taken daily in water with food, or directly under the tongue, or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "If you are under medical supervision, please consult a doctor before use.\n" +
                        "Store below 25°C in a dry place away from direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_nutrisorb_liquid_vitamin_e"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_nutrisorb_liquid_vite_750",
                "Nutrisorb® Liquid Iodine 15ml",
                "Nutrisorb® Liquid Iodine drops as bioavailable potassium iodide in a simple, hypoallergenic purified water base.\n",
                "Specific Nutrient Complexes",
                "15ml",
                0,
                "Purified Water, Potassium Iodide, Citric Acid, Preservative (Potassium Sorbate). ",
                "• Nutrisorb® liquids are a \n" +
                        "range of high potency\n" +
                        " liquid nutrients\n" +
                        "• Iodine contributes to the normal production of thyroid hormones and normal thyroid function\n" +
                        "• Iodine contributes to normal functioning of the nervous system\n" +
                        "• Iodine contributes to normal cognitive function\n" +
                        "• 150mcg high quality iodine drops\n",
                "One drop taken daily in water with food, or directly under the tongue or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Do not purchase if the seal is broken\n",
                ImageRetriever.createUrl("biocare_nutrisorb_liquid_vite_750"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_pregnancylactation",
                "Pregnancy & Lactation Formula - 60 Capsules",
                "Pregnancy & Lactation Formula contains a blend of nutrients, in biologically active forms important during pregnancy and breastfeeding. It includes folic acid at recommended levels along with vitamin B12, iron, zinc and vitamin A.\n",
                "Specific Nutrient Complexes",
                "60",
                0,
                "Magnesium Citrate, Vitamin C (as Magnesium Ascorbate), Calcium Citrate, Capsule Shell (HPMC), Choline Bitartrate, Iron Citrate, Zinc Citrate, Vitamin E (as D-Alpha Tocopheryl Acetate), Corn Starch, Niacin (as Nicotinamide), Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide),Dicalcium Phosphate, Silicon Dioxide, Olive Oil, Modified Tapioca Starch, Pantothenic Acid (as Calcium Pantothenate), Microcrystalline Cellulose, Inositol, PABA (Para Amnio Benzoic Acid), Acacia Gum, Copper Gluconate, Beta Carotene, Vitamin B6 (as Pyridoxine Hydrochloride), Sunflower Oil, Thiamine (as Thiamine Hydrochloride), Riboflavin, Sucrose, Vitamin A (as Retinyl Palmitate), Manganese Citrate, Folic Acid, Chromium Picolinate, Antioxidants (Ascorbic Acid, Ascorbyl Palmitate & Natural Mixed Tocopherols), Potassium Iodide, Vitamin K1 (as Phylloquinone), L-Selenomethionine, Sodium Molybdate, Biotin, Vitamin D2 (as Ergocalciferol), Vitamin B12 (as Hydroxycobalamin).  ",
                "• A combination of nutrients balanced to support the physiological requirements of pregnancy\n" +
                        "• Provides nutrients in their most biologically active forms to assist absorption\n" +
                        "• Can be used prior to, during and after pregnancy\n" +
                        "• The Department of Health recommends that women who could become pregnant or who are already pregnant take Folic Acid daily at 400 micrograms  before conception and throughout the first 12 weeks of pregnancy\n" +
                        "• Folic acid contributes to maternal tissue growth during pregnancy\n" +
                        "• Contains 600µg of vitamin A, a level considered safe during pregnancy\n" +
                        "• Provides vitamin C in a buffered, low-acid form to reduce stomach irritation\n" +
                        "• Vitamin B12, C, B6, Iron, Magnesium and Folate, contribute to the reduction of tiredness and fatigue\n",
                "One capsule taken twice daily with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product contains iron, which, if taken in excess, may be harmful to very young children. Keep out of sight and reach\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_pregnancylactation"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_immunoberry",
                "ImmunoBerry® (Elderberry with Zinc) 150ml",
                "An immune support formulation with highly concentrated elderberry and zinc. This delicious fruity liquid is a perfect winter supplement for adults. Children's Elderberry Complex is also available.\n",
                "Herbal",
                "150ml",
                0,
                "Fructose, Elderberry Concentrate (Sambucus nigra Berry), Water, Glycerol, Acacia Gum, Zinc Gluconate, Preservative (Potassium Sorbate).\n",
                "• Convenient liquid form, ideal for those who are unable or prefer not to swallow capsules or tablets\n" +
                        "• Zinc contributes to the normal function of the immune system\n" +
                        "• Elderberry contributes to the normal function of the immune system\n",
                "One teaspoon (5ml) taken twice daily with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Vacuum sealed for stability\n" +
                        "Once opened, refrigerate and consume within 4 months\n",
                ImageRetriever.createUrl("biocare_immunoberry"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_childrens_elderberry",
                "Children's Elderberry Complex 150ml",
                "Children's Elderberry Complex has been especially designed for children.  Presented as a delicious fruity liquid it can be used in drinks or mixed into foods.  Children's Elderberry Complex also contains zinc and vitamin C.\n",
                "Herbal",
                "150ml",
                0,
                "Water, Glycerol, Fructose, Elderberry Concentrate (Sambucus nigra Berry), Acacia Gum, Vitamin C (as Magnesium Ascorbate), Zinc Gluconate, Preservative (Potassium Sorbate). ",
                "• Specially formulated for children and suitable from three years old\n" +
                        "• Delicious fruity liquid that can be mixed into liquids or food\n" +
                        "• Elderberry naturally contains anthocyanins\n",
                "Take daily with food as indicated or as professionally directed.\n" +
                        "3 - 6 years - Two teaspoons (10ml), 7 years and over - Three teaspoons (15ml).\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        " This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Vacuum sealed for stability\n" +
                        "Once opened, refrigerate and consume within 4 months\n",
                ImageRetriever.createUrl("biocare_childrens_elderberry"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_methylbcomplex",
                "Methyl B Complex - 60 Capsules",
                "Methyl B Complex is a superior B complex, providing nutrients in their metabolically active, bioavailable forms.\n",
                "Vitamins",
                "60",
                0,
                "Capsule Shell (HPMC), Vitamin B6 (as Pyridoxal-5-Phosphate), Thiamine (as Thiamine Mononitrate), Pantothenic Acid (as Calcium Pantothenate), Inositol, Riboflavin, Choline Bitartrate, Niacin (as Nicotinamide), Para Amino Benzoic Acid (PABA), Dicalcium Phosphate, L-Glycine, Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Bulking Agent (Microcrystalline Cellulose), Folate (as Quatrefolic® [(6S)-5-methyltetrahydrofolic acid glucosamine salt]), Vitamin B12 (as Methylcobalamin & 5-Deoxyadenosylcobalamin), Biotin.  ",
                "• Methyl B Complex is a superior B complex, providing nutrients in their naturally active forms\n" +
                        "• Methyl B Complex contains folate as methylfolate or 5-MTHF. 5-MTHF is the natural form of folate found in food such as leafy green vegetables and is already active and ready for the body to use\n" +
                        "• We use Quatrefolic®, the most advanced form of folate supplementation available, with optimum stability and bioavailability\n" +
                        "• B vitamins are required for the release of energy from food, for the healthy function of the nervous system and for the production of hormones\n" +
                        "• B vitamins support the circulatory and immune systems and help maintain the health of skin, hair and eyes\n" +
                        "• Vitamin B2, B3, B5, B6 and B12 contribute to the reduction of tiredness and fatigue\n" +
                        "• Methyl B Complex is presented in a simple, hypoallergenic capsule\n" +
                        "• Vacuum packed to maximise stability and freshness, yeast free and suitable for vegetarians and vegans\n",
                "One capsule taken daily with food, or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Long term intake of amounts greater than 10mg of vitamin B6 daily may lead to mild tingling and numbness\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Vacuum sealed for stability\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_methylbcomplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_methylmultinutrient",
                "Methyl Multinutrient",
                "Methyl Multinutrient is a superior, high potency multivitamin, mineral and antioxidant complex, providing nutrients in metabolically active, bioavailable forms.",
                "Multi-Nutrients",
                "60 & 120",
                0,
                "Magnesium Citrate, Vitamin C (as Magnesium Ascorbate), Capsule Shell (HPMC), Vitamin B5 (as Calcium Pantothenate), Vitamin B6 (as Pyridoxal-5-Phosphate), Dicalcium Phosphate, Vitamin B1 (as Thiamine Mononitrate), Riboflavin, Niacin (as Nicotinamide), Vitamin K (as K2 Menaquinone-7 (Soya)), Sucrose, Acacia Gum, Corn Starch, Medium Chain Triglycerides, Tricalcium Phosphate, Zinc Citrate, Vitamin B12 (as Methylcobalamin), Sodium Selenite, Iron Citrate, Vitamin E (as D-Alpha Tocopheryl Succinate & Natural Mixed Tocotrienols & Tocopherols, Modified Corn Starch, Mono/Di and Triglycerides, Choline Bitartrate, Microcrystalline Cellulose, Sodium Molybdate, Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide), Bulking Agent (Cellulose), Vitamin D3 (preparation as Cholecalciferol (Lichen) with Tapioca Maltodextrin, Corn Starch, Coconut Oil, Sucrose, Silicon Dioxide, D-Alpha Tocopherol, Ascorbyl Palmitate), Vitamin A (as Retinyl Palmitate), Modified Tapioca Starch, Antioxidant (Natural Mixed Tocopherols), Sunflower Oil, Inositol, Para Amino Benzoic Acid (PABA), Grapeseed Extract (Vitus vinifera Seed as Vitaflavan®), Manganese Gluconate, Chromium Chloride, Potato Maltodextrin, Potassium Iodide, Folate (as Quatrefolic® [(6S)-5-Methyltetrahydrofolic acid glucosamine salt]), Biotin, Copper Citrate.  ",
                "• Methyl Multinutrient contains folate as methylfolate or 5-MTHF. 5-MTHF is the natural form of folate found in food such as leafy green vegetables and is already active and ready for the body to use\n" +
                        "• We use Quatrefolic®, the most advanced form of folate supplementation available, with optimum stability and bioavailability\n" +
                        "• B vitamins are required for the release of energy from food, for the healthy function of the nervous system and for the production of hormones\n" +
                        "• B vitamins support the circulatory and immune systems and help maintain the health of skin, hair and eyes\n",
                "Two capsules taken daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Not suitable for use during pregnancy, planned pregnancy or breastfeeding\n" +
                        "Long term intake of amounts greater than 10mg vitamin B6 daily may lead to mild tingling and numbness\n" +
                        "This product contains iron, which, if taken in excess, may be harmful to young children. Keep out of sight and reach of children\n" +
                        "If you are taking anti-coagulant medication, do not take this product except on the advice of a doctor\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_methylmultinutrient"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_mixedascorbates_250g",
                "Mixed Ascorbates (Vitamin C & fruit extracts) 250g",
                "Mixed Ascorbates is a great tasting vitamin C powder with minerals and berry extracts. This handy powder dissolves easily into water.  Each 5g dose provides 1g of vitamin C with minerals such as zinc and manganese. \n",
                "Vitamins",
                "250g",
                0,
                "Fructose, Potato Maltodextrin, Vitamin C (as Ascorbic\n" +
                        "Acid), Magnesium Ascorbate, Apricot (Prunus armeniaca\n" +
                        "Fruit), Blackberry (Rubus fruticosus Fruit), Bilberry\n" +
                        "(Vaccinium myrtillus Berry), Potassium Ascorbate,\n" +
                        "Manganese Ascorbate, Calcium Ascorbate, Zinc Ascorbate,\n" +
                        "Cherry (Prunus avium Fruit), Citric Acid. ",
                "• Provides a readily absorbable form of vitamin C together with bioavailable forms of zinc and manganese\n" +
                        "• Buffered, low acid form of vitamin C to reduce stomach irritation\n" +
                        "• Powder form that is suitable for people who have difficulty or prefer not to swallow capsules or tablets\n" +
                        "• Vitamin C contributes to normal functioning of the nervous system\n" +
                        "• Vitamin C contributes to normal psychological function\n" +
                        "• Vitamin C and zinc contribute to the normal function of the immune system\n" +
                        "• Vitamin C, manganese and zinc contribute to the protection of cells from oxidative stress\n" +
                        "• Vitamin C contributes to the reduction of tiredness and fatigue\n" +
                        "• Vitamin C increases iron absorption\n" +
                        "• Manganese contributes to normal energy-yielding metabolism\n" +
                        "• Zinc contributes to normal DNA synthesis\n",
                "One teaspoon (approx 5 grams) taken daily (mixed into liquid) with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Long term intake of amounts greater than 4mg of Manganese daily may lead to muscle pain and fatigue\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_mixedascorbates_250g"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_hepaguardforte",
                "HepaGuard Forte® 60 Capsules",
                "HepaGuard Forte® is a specialist combination including choline bitartrate, inositol, sodium sulphate, artichoke extract, taurine, apple extract and L-methionine.  This product is suitable for vegetarians and vegans.\n",
                "Specific Nutrient Complexes",
                "60",
                0,
                "Choline Bitartrate, Capsule Shell (HPMC), Inositol, Sodium Sulphate, L-Taurine, L-Methionine, Apple Extract (Malus spp), Artichoke Extract 5:1 (Cynara scolymus leaf), Corn Maltodextrin, Alpha Lipoic Acid, Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide), Bulking Agent (Cellulose), Green Tea Extract (Camellia sinensis leaf), Riboflavin, Silicon Dioxide, Biotin. ",
                "• Suitable for vegetarians and vegans\n" +
                        "• Riboflavin & biotin contribute to normal energy-yielding metabolism\n" +
                        "• Riboflavin & biotin contribute to normal functioning of the nervous system\n" +
                        "• Riboflavin & biotin contribute to the maintenance of normal mucous membranes\n" +
                        "• Riboflavin contributes to the maintenance of normal red blood cells\n" +
                        "• Riboflavin contributes to the protection of cells from oxidative stress\n" +
                        "• Riboflavin contributes to the reduction of tiredness and fatigue\n" +
                        "• Biotin contributes to normal macronutrient metabolism\n" +
                        "• Biotin contributes to normal psychological function\n" +
                        "• Biotin contributes to the maintenance of normal hair & skin\n" +
                        "• Artichoke contributes to normal blood lipid levels and may support detoxification\n",
                "One capsule taken twice daily with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_hepaguardforte"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_oneadayvitaminsminerals",
                "One A Day Vitamins & Minerals",
                "A premium strength, broad spectrum, one-a-day multinutrient. It has a high mineral bioavailability including iron and boron.   An ideal everyday supplement and even suitable for those with dietary intolerances.\n",
                "Vitamins",
                "30 & 60 & 90",
                0,
                "Bulking Agents (Dicalcium Phosphate & Microcrystalline Cellulose), Vitamin C (as Magnesium Ascorbate), Calcium Citrate, Niacin (as Nicotinamide), Vitamin E (as D-Alpha Tocopheryl Succinate), Modified Tapioca Starch, Pantothenic Acid (as Calcium Pantothenate), Choline Bitartrate, Magnesium Citrate, Zinc Citrate, Tablet Coating [Glazing Agents (Hydroxypropyl Methylcellulose & Glycerin) & Colours (Titanium Dioxide & Riboflavin)], Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Acacia Gum, Thiamine (as Thiamine Hydrochloride), Manganese Ascorbate, Vitamin B6 (as Pyridoxine Hydrochloride), Cellulose, Olive Oil, Riboflavin, Inositol, Para Amino Benzoic Acid, Iron Fumarate, Bilberry Extract (Vaccinium myrtillus Fruit), Sodium Carboxymethyl Cellulose, Sodium Borate, Lutein, Alginate, Soy Protein, Cellulose, Beta Carotene, Vitamin A (as Retinyl Palmitate), Sodium Molybdate, Sunflower Oil, Chromium Picolinate, Sucrose, Corn Starch, Antioxidants (Ascorbic Acid, Ascorbyl Palmitate & Natural Mixed Tocopherols), Sodium Selenite, Folic Acid, Biotin, Potassium Iodide, Vitamin B12 (as Hydroxycobalamin), Vitamin D (as Ergocalciferol).  ",
                "• Optimal bioavailabilty\n" +
                        "• Formulated with people with allergies and intolerances in mind\n" +
                        "• Contains iron\n" +
                        "• Convenient one a day formula\n" +
                        "• Tried and tested for WADA (World Anti-Doping Agency) banned substances to ensure that they are safe for professional sports persons to use\n" +
                        "• Vitamin E contributes to the protection of cells from oxidative stress\n" +
                        "• Vitamin B5 contributes to normal mental performance\n" +
                        "• Vitamin C contributes to the protection of cells from oxidative stress\n" +
                        "• Vitamin B3 contributes to the normal functioning of the nervous system, psychological function, and normal energy-yielding metabolism\n" +
                        "• Provides vitamin C in a low acidity form as magnesium ascorbate\n" +
                        "• Vitamin C contributes to the normal function of the immune system and the normal collagen formation for the normal function of bones, blood vessels, cartilage, gums, skin & teeth\n" +
                        "• Contains vitamin D2 - suitable for vegans\n" +
                        "• Vitamin D contributes to the normal function of the immune system and to normal blood calcium levels\n" +
                        "• Vitamin B6 contributes to the reduction of tiredness and fatigue\n" +
                        "• B vitamins contribute to normal energy-yielding metabolism\n" +
                        "• Contains yeast-free selenium and natural vitamin E\n" +
                        "•  NB: NOT FOR SALE IN THE UNITED STATES\n" +
                        "• For full allergen information, scroll over label above\n",
                "One tablet taken daily with food or as professionally directed.\n",
                "This product contains iron, which, if taken in excess, may be harmful to very young children. Keep out of reach of children\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Long term intake of amounts greater than 10mg vitamin B6 daily may lead to mild tingling and numbness\n" +
                        "Long term intake of amounts greater than 4mg of manganese daily may lead to muscle pain and fatigue\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_oneadayvitaminsminerals"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_microcellvitamine200iu",
                "MicroCell® Vitamin E 200iu 60 Capsules",
                "Vitamin E is an antioxidant that contributes to the protection of cells from oxidative stress. Our Vitamin E is micellised by our unique MicroCell® process to enhance absorption and bioavailability and provides 400iu natural vitamin E per day.\n",
                "Vitamins",
                "60",
                0,
                "Modified Tapioca Starch, Vitamin E (as D-Alpha Tocopherol Acetate),Capsule Shell (HPMC & Colours (Titanium Dioxide & Natural Copper Chlorophyllin),Sunflower Oil, Anti-caking Agents (Silicon Dioxide & Magnesium Stearate). ",
                "• Micellised into small droplets using our unique MicroCell® process for enhanced absorption and bio-availability\n" +
                        "• Provided in a light-protective chlorophyll vegetable capsule to enhance stability against ultraviolet light\n" +
                        "• Vitamin E is an antioxidant that contributes to the protection of cells from oxidative stress\n",
                "One capsule taken twice daily with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_microcellvitamine200iu"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitamind31000iu",
                "Vitamin D3 1000iu - 60 Capsules",
                "High potency vitamin D3 providing 1000iu vitamin D per capsule for optimum support.\n\n",
                "Vitamins",
                "60",
                0,
                "Bulking Agent (Microcrystalline Cellulose), Capsule Shell (HPMC), Vitamin D3 (Preparation as Cholecalciferol (Lichen) with Maltodextrin, Corn Starch, Sucrose, Silicon Dioxide & Ascorbyl Palmitate), Anti-Caking Agent Magnesium Stearate). ",
                "• Our vitamin D capsules are sourced from lichen and are free from allergens\n" +
                        "• Vitamin D helps maintain blood levels of calcium and phosphorus, helps form and maintain strong bones and is required for the normal functioning of the immune system\n" +
                        "• Suitable for vegetarians and vegans\n" +
                        "• 60 days' supply\n",
                "One capsule taken daily with food, or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_vitamind31000iu"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_bcomplex25",
                "B Complex 25  30 Capsules",
                "A low potency B complex supplement containing B vitamins and associated nutrients, B Complex 25 is also suitable from 5 years of age and the capsule can be opened if necessary to take as a powder.\n",
                "Vitamins",
                "30",
                0,
                "Capsule Shell (HPMC), Bulking Agent (Cellulose), Vitamin C (as Magnesium Ascorbate), Thiamine (as Thiamine Hydrochloride), Vitamin B6 (as Pyridoxal-5-Phosphate), Pantothenic Acid (as Calcium Pantothenate), Riboflavin, Niacin (as Niacinamide), Choline Bitartrate, Inositol, Para Amino Benzoic Acid, L-Glycine, , Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Biotin, Folic Acid, Vitamin B12 (as Hydroxycobalamin). ",
                "• Can be used for children between 5 and 14 years of age\n" +
                        "• Vacuum packed for stability\n" +
                        "• Easy to swallow small capsule\n" +
                        "• Vitamins B6 and B12 are presented in their biologically active forms\n" +
                        "• Vacuum packed to reduce oxidation of the B vitamins\n" +
                        "• Vitamins B2, B6, B12, C and biotin contribute to normal functioning of the nervous system\n" +
                        "• Vitamin B1, B3, B6 and B12 contribute to normal psychological function\n" +
                        "• Vitamin B1, B2, B3, B5 and B6 contribute to normal energy-yielding metabolism\n" +
                        "• Vitamin B2, B3, B5, B6 and B12 contributes to the reduction of tiredness and fatigue\n" +
                        "• Vitamin B6, B12, folic acid and vitamin C all contribute to the normal function of the immune system       \n",
                "One capsule taken daily with food or as professionally directed.\n",
                "Long term intake of amounts greater than 10mg vitamin B6 daily may lead to mild tingling and numbness\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Vacuum sealed for stability\n",
                ImageRetriever.createUrl("biocare_bcomplex25"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_garlicplus",
                "Garlic Plus 90 Capsules",
                "Garlic Plus is a combination of freeze-dried garlic containing the active constituent allicin, with the addition of biotin. The process of gentle freeze-drying maintains the active components of garlic in tact.\n",
                "Herbal",
                "90",
                0,
                "Freeze-Dried Garlic Powder (Allium sativum Clove), Capsule Shell (HPMC), Anti-Caking Agent (Magnesium Stearate), Biotin. ",
                "• High potency product, freeze-dried to preserve and optimise the active components.\n" +
                        "• Biotin contributes to the maintenance of normal mucous membranes\n" +
                        "• Garlic contributes to normal immune function\n" +
                        "• Garlic has antibacterial effects\n" +
                        "• Garlic may help maintain heart health, maintain normal cholesterol levels and contributes to vascular health\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "Store below 25oC in a dry place away from direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_garlicplus"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitaminc_powder",
                "Vitamin C Powder",
                "Vitamin C Powder contains magnesium ascorbate which is a low-acid form of vitamin C, produced by our unique solvent-free freeze-dried method; it is free from additives and excipients. This form of vitamin C is gentler on the stomach.\n",
                "Vitamins",
                "60g & 250g",
                0,
                "Vitamin C (as Magnesium Ascorbate).  ",
                "• Provides a readily absorbable form of vitamin C and magnesium\n" +
                        "• Buffered, low acid form of vitamin C to reduce stomach irritation\n" +
                        "• Magnesium ascorbate does not leach minerals such as calcium and potassium from the body\n" +
                        "• Suitable for intolerant individuals and those requiring a citrus-free vitamin C supplement\n" +
                        "• Powder form makes it suitable for those who have difficulty swallowing capsules or tablets\n" +
                        "• Pure freeze-dried powder, additive, solvent and excipient free\n" +
                        "• Vitamin C contributes to maintain the normal function of the immune system during and after intense physical exercise\n" +
                        "• Vitamin C contributes to normal collagen formation for the normal function of blood vessels, bones, cartilage, gums, skin and teeth\n" +
                        "• Vitamin C and magnesium contribute to normal energy-yielding metabolism\n" +
                        "• Vitamin C contributes to normal functioning of the nervous and immune systems\n" +
                        "• Vitamin C and magnesium contribute to normal psychological function\n" +
                        "• Vitamin C is an antioxidant that contributes to the protection of cells from oxidative stress\n" +
                        "• Vitamin C and magnesium contribute to the reduction of tiredness and fatigue\n" +
                        "• Vitamin C contributes to the regeneration of the reduced form of vitamin E\n" +
                        "• Vitamin C increases iron absorption\n" +
                        "• Magnesium contributes to normal functioning of the nervous system\n" +
                        "• Magnesium contributes to normal muscle function\n",
                "One scoop (1 gram) taken twice daily with food or as professionally directed. Vitamin C Powder can be mixed with water or juice.\n",
                "Amounts greater than 1000mg of vitamin C daily may cause mild stomach upset in sensitive individuals\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_vitaminc_powder"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_enteroguard",
                "EnteroGuard® 150g",
                "EnteroGuard® contains F.O.S., L-glutamine, vitamin C, N-acetyl glucosamine, zinc and vitamin A. Vitamin A, C and zinc contribute to the normal function of the immune system and vitamin A contributes to the maintenance of normal mucous membranes.\n",
                "Gastrointestinals",
                "150g",
                0,
                "Fructooligosaccharides (Chicorium intybus Root), L-Glutamine, Vitamin C (as Magnesium Ascorbate), Apricot Powder (Prunus armeniaca Fruit), N-Acetyl Glucosamine (Crustaceans) Zinc Ascorbate, Modified Tapioca Starch, Vitamin A (as Retinyl Palmitate), Antioxidant (Natural Mixed Tocopherols), Sunflower Oil. ",
                "• Convenient powder form to be mixed in water, ideal for those who have difficulty or prefer not to swallow capsules or tablets\n" +
                        "• Magnesium ascorbate is a bioavailable, buffered, low-acid form of vitamin C and magnesium\n" +
                        "• Zinc ascorbate is a buffered, low-acid bioavailable source of vitamin C and zinc\n" +
                        "• Vitamin A, C and zinc contribute to the normal function of the immune system\n" +
                        "• Vitamin C and zinc contribute to the protection of cells from oxidative stress\n" +
                        "• Magnesium and zinc have a role in the process of cell division\n" +
                        "• Zinc contributes to normal macronutrient metabolism\n" +
                        "• Zinc contributes to normal metabolism of fatty acids  \n" +
                        "• Vitamin A contributes to the maintenance of normal mucous membranes\n" +
                        "• Contains fructooligosaccharides (F.O.S.) derived from chicory\n" +
                        "• For full allergen information, scroll over label above\n",
                "Two teaspoons (approximately 10g) mixed into water and taken daily with food, or as professionally directed.\n",
                "Amounts greater than 1gram of vitamin C may cause mild stomach upset in sensitive individuals\n" +
                        "This product contains vitamin A. Do not take if you are pregnant or likely to become pregnant except on the advice of a doctor or ante-natal clinic\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product can initally cause wind and bloating\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Long term intake of amounts greater than 25mg of zinc daily may lead to anaemia\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_enteroguard"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_oregano_complex",
                "Oregano Complex - 90 Capsules",
                "A combination of concentrated food grade oils which are freeze-dried and micellised to promote absorption and bioavailability. Oregano Complex provides garlic, grapeseed oil, borage seed oil, oregano oil, clove oil, ginger grass oil and vitamin E.\n",
                "Gastrointestinals",
                "90",
                0,
                "Modified Tapioca Starch, Capsule Shell (HPMC), Grapeseed Oil (Vitus vinifera Seed), Borage Oil (Borago Officinalis Seed), Oregano Oil (Origanum vulgare Leaf), Clove Oil (Eugenia caryophyllus Leaf), Ginger Grass Oil (Cymbopogon martini Grass), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Garlic Extract (Allium Sativum Clove), Vitamin E (as D-Alpha Tocopheryl Acetate), Sunflower Oil.  ",
                "• Oregano Complex is a combination of concentrated food grade oils\n" +
                        "• These oils have been freeze-dried and micellised to water-soluble compounds to promote absorption and bioavailability\n" +
                        "• Oregano Complex provides multi-systemic and broad spectrum activity in a single capsule\n" +
                        "• Oregano oil may be helpful for stomach and gut health\n",
                "One capsule taken three times daily with food or as professionally directed.\n",
                "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "Caution is advised in individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "Caution is advised with anti-coagulant medication\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Not suitable for individuals suffering from epilepsy\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_oregano_complex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_optizinc",
                "OptiZinc® 60 Capsules",
                "OptiZinc® includes L-methionine and zinc. Zinc methionine exhibits a superior absorption rate when compared to many other forms of zinc and provides 14mg elemental zinc per daily intake.\n",
                "Minerals",
                "60",
                0,
                "Bulking Agent (Cellulose), Capsule Shell (HPMC), Zinc Methionine (OptiZinc®), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Natural Vanilla Flavour. ",
                "• Zinc contributes to normal cognitive function\n" +
                        "• Zinc contributes to the maintenance of normal hair, skin, nails and bones\n" +
                        "• Zinc contributes to the normal metabolism of vitamin A\n" +
                        "• Zinc contributes to normal fertility and reproduction\n" +
                        "• Zinc contributes to the maintenance of normal testosterone levels in the blood\n" +
                        "• Zinc contributes to normal DNA synthesis and is an antioxidant that contributes to the protection of cells from oxidative damage\n" +
                        "• L-Methionine is an amino acid\n" +
                        "• Zinc contributes to normal metabolism of fatty acids\n",
                "One capsule taken twice daily with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_optizinc"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitaminb1",
                "Vitamin B1 30 Veg Capsules",
                "Vitamin B1 (thiamine) is a water-soluble B vitamin which contributes to normal energy yielding metabolism, normal psychological, heart and nervous system function. Our Vitamin B1 is yeast-free and provides 100mg per capsule.\n",
                "Vitamins",
                "30",
                0,
                "Bulking Agent (Cellulose), Thiamine (Vitamin B1 as Thiamine Hydrochloride), Capsule Shell (HPMC), Anti-Caking Agent ( Magnesium Stearate). ",
                "• Yeast-free\n" +
                        "• Thiamine contributes to normal energy-yielding metabolism\n" +
                        "• Thiamine contributes to normal functioning of the nervous system\n" +
                        "• Thiamine contributes to normal psychological function\n" +
                        "• Thiamine contributes to the normal function of the heart\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_vitaminb1"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitaminb2",
                "Vitamin B2 30 Veg Capsules",
                "Vitamin B2 (riboflavin) is needed for many body functions including normal energy-yielding metabolism, maintenance of normal skin and vision, and the reduction of tiredness and fatigue. Our Vitamin B2 provides 50mg in capsule form.\n",
                "Vitamins",
                "30",
                0,
                "Bulking Agent (Cellulose), Capsule Shell (HPMC), Riboflavin (Vitamin B2), Anti-Caking Agent (Magnesium Stearate).  ",
                "• Yeast free\n" +
                        "• Riboflavin contributes to normal energy-yielding metabolism\n" +
                        "• Riboflavin contributes to normal functioning of the nervous system\n" +
                        "• Riboflavin contributes to the maintenance of normal mucous membranes and red blood cells\n" +
                        "• Riboflavin contributes to the maintenance of normal skin and vision\n" +
                        "• Riboflavin contributes to the normal metabolism of iron\n" +
                        "• Riboflavin contributes to the protection of cells from oxidative stress\n" +
                        "• Riboflavin contributes to the reduction of tiredness and fatigue\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_vitaminb2"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitaminb3",
                "Vitamin B3 30 Veg Capsules",
                "Vitamin B3 (niacin) is useful for many functions in the body including maintenance of normal mucosal membranes and normal skin, also normal energy-yielding metabolism. Our Vitamin B3 is  a non flushing form.\n",
                "Vitamins",
                "30",
                0,
                "Bulking Agent (Cellulose), Niacin (as Nicotinamide), Capsule Shell (HPMC, Anti-Caking Agent (Magnesium Stearate)  ",
                "• Yeast-free\n" +
                        "• Niacin contributes to normal energy-yielding metabolism\n" +
                        "• Niacin contributes to normal functioning of the nervous system\n" +
                        "• Niacin contributes to normal psychological function\n" +
                        "• Niacin contributes to the maintenance of normal mucous membranes\n" +
                        "• Niacin contributes to the maintenance of normal skin\n" +
                        "• Niacin contributes to the reduction of tiredness and fatigue\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_vitaminb3"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_vitaminb5",
                "Vitamin B5 60 Capsules",
                "A high potency vitamin B5 supplement with magnesium in a convenient one a day capsule. Pantothenic acid contributes to the normal synthesis and metabolism of steroid hormones, vitamin D and some neurotransmitters.\n",
                "Vitamins",
                "60",
                0,
                "Pantothenic Acid (as Calcium Pantothenate), Magnesium Glycerophosphate Capsule Shell (HPMC), Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide). ",
                "• Formerly Magnesium Plus Pantothenate\n" +
                        "• Yeast-free\n" +
                        "• Convenient one a day capsule\n" +
                        "• Pantothenic acid and magnesium contribute to normal energy-yielding metabolism\n" +
                        "• Pantothenic acid contributes to normal mental performance\n" +
                        "• Pantothenic acid contributes to normal synthesis and metabolism of steroid hormones, vitamin D and some neurotransmitters\n" +
                        "• Magnesium is needed for normal nerve and muscle function\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_vitaminb5"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_megaepa",
                "Mega EPA (Omega-3 Fish Oil)",
                "Mega EPA is a high potency fish oil capsule providing omega-3 fatty acids in a natural triglyceride form, naturally concentrated using NEO-3™ - a unique lipase enzyme process which results in a fish oil of outstanding quality and high potency.\n",
                "Fatty Acids",
                "30 & 60 & 90",
                0,
                "Fish Oil, Capsule Shell (Fish Gelatin & Glycerin), Sweet Orange Oil, Natural Mixed Tocopherols, Sunflower Oil.  ",
                "• Mega EPA is ideal for daily use and can also be used during pregnancy\n" +
                        "• Provides fish oil from anchovies and sardines and is free from detectable PCBs and contaminants\n" +
                        "• The fish oil is naturally concentrated and pre-digested using our patented NEO-3™ process, utilising lipase enzymes\n" +
                        "• Utilises our 'Multox' antioxidant system for enhanced stability\n" +
                        "• Suitable for use during pregnancy\n" +
                        "• DHA contributes to maintenance of normal brain function\n" +
                        "• DHA contributes to the maintenance of normal vision\n" +
                        "• EPA and DHA contribute to the normal function of the heart\n" +
                        "• Maternal intake of Docosahexaenoic acid (DHA) contributes to the normal brain and eye development of the foetus and breastfed infants\n" +
                        "• For full allergen information, scroll over label above\n",
                "One capsule taken twice daily with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Caution is advised with anti-coagulant medication\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_megaepa"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_ironcomplex",
                "Iron Complex 90 Capsules",
                "Our Iron Complex contains iron with malic acid, vitamin C and vitamin B2 for optimum iron absorption.  It is particularly suitable for individuals who are sensitive to standard iron supplementation.\n",
                "Minerals",
                "90",
                0,
                "Vitamin C (as Ascorbic Acid), Malic Acid, Iron Citrate, Capsule Shell (HPMC) , Bulking Agent (Cellulose), Riboflavin (Vitamin B2), Anti-caking Agent ( Magnesium Stearate). ",
                "• Useful for individuals with malabsorption and poor iron status\n" +
                        "• Particularly suited to those individuals who are sensitive to inorganic iron supplementation\n" +
                        "• Iron contributes to normal oxygen transport in the body\n" +
                        "• Iron, vitamin B2 and vitamin C contribute to the reduction of tiredness and fatigue\n" +
                        "• Iron contributes to normal cognitive function\n" +
                        "• Iron contributes to normal formation of red blood cells and haemoglobin\n" +
                        "• Vitamin C and vitamin B2 are antioxidants that contribute to the protection of cells from oxidative stress\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product contains iron, which, if taken in excess, may be harmful to very young children. Keep out of sight and reach\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_ironcomplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_microcellcoq10pluslinseed",
                "MicroCell® CoQ10 Plus Linseed 60 Capsules",
                "This potent combination of co-enzyme Q10, linseed oil and vitamin E is micellised to significantly increase absorption and bioavailability.  Linseed oil contains 58% omega-3 fatty acids and 14% omega-6 fatty acids .\n",
                "Fatty Acids",
                "60",
                0,
                "Potato Maltodextrin, Linseed Oil (Flaxseed Oil), Modified Tapioca Starch Capsule Shell (HPMC & Colours (Titanium Dioxide & Copper Chlorophyllin)), CoEnzyme Q10, Silicon Dioxide, Vitamin E (as D-Alpha Tocopherol Acetate), Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide), Lemon Puree (Sulphur Dioxide), Antioxidants (Ascorbic Acid, Ascorbyl Palmitate & Natural Mixed Tocopherols), Acacia Gum, Sunflower Oil, Preservative (Sulphur Dioxide).   ",
                "• Formerly MicroCell CoQ10 Plus\n" +
                        "• Highly bio-available micellised CoQ10 and linseed oil\n" +
                        "• Linseed oil (flaxseed oil) contains 58% omega-3 and 14% omega-6 fatty acids and provides a useful vegan form of ALA; an omega 3 fatty acid\n" +
                        "• MicroCell® nutritional oils are encapsulated in chlorophyll vegetable capsules to maintain stability\n" +
                        "• For full allergen information, scroll over label above\n",
                "One capsule taken twice daily with food or as professionally directed.\n",
                "Caution is advised with anti-coagulant medication\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_microcellcoq10pluslinseed"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_megaepaforte",
                "Mega EPA Forte (Omega-3 Fish Oil)",
                "Mega EPA Forte is an extra high potency fish oil capsule, suitable for daily use. EPA and DHA contribute to the normal function of the heart and DHA contributes to the maintenance of normal brain and vision functions.\n",
                "Fatty Acids",
                "60 & 120",
                0,
                "Fish Oil, Capsule Shell (Fish Gelatin & Glycerin), Sweet Orange Oil, Natural Mixed Tocopherols, Sunflower Oil, D-Alpha Tocopherol. ",
                "• High potency capsule suitable for daily use\n" +
                        "• Provides fish oil from anchovies and sardines and is free from detectable PCBs and contaminants\n" +
                        "• Highly concentrated triglyceride form\n" +
                        "• DHA contributes to the maintenance of normal brain function\n" +
                        "• DHA contributes to the maintenance of normal vision\n" +
                        "• EPA and DHA contribute to the normal function of the heart\n" +
                        "• Maternal intake of Docosahexaenoic acid (DHA) contributes to the normal brain and eye development of the foetus and breastfed infants\n",
                "One capsule taken twice daily with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Caution is advised with anticoagulant medication\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n",
                ImageRetriever.createUrl("biocare_megaepaforte"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_sucroguard",
                "SucroGuard Capsules",
                "SucroGuard® is a blend of key nutrients including chromium, manganese and vitamins B1, B2, niacin and B6.  Chromium contributes to the maintenance of normal blood glucose levels and to macronutrient metabolism.\n",
                "Specific Nutrient Complexes",
                "30 & 90",
                0,
                "Vitamin C (as Magnesium Ascorbate), Magnesium Citrate, Capsule Shell (HPMC), Magnesium Malate, Pantothenic Acid (as Calcium Pantothenate), Vitamin B6 (as Pyridoxine-5-Phosphate), Niacin (as Nicotinamide), Thiamine (as Thiamine Hydrochloride), Riboflavin, Manganese Citrate, Anti-Caking Agent (Magnesium Stearate), Chromium Picolinate. ",
                "• Provides Krebs cycle intermediates\n" +
                        "• Provides pantothenic acid as the buffered salt of magnesium pantothenate\n" +
                        "• Provides chromium which contributes to the maintenance of normal blood glucose levels and nutrient metabolism\n" +
                        "• Magnesium malate is a source of readily absorbable magnesium \n" +
                        "• Magnesium ascorbate is the preferred form of buffered, low-acid vitamin C and is highly bioavailable\n" +
                        "• Pantothenic acid, vitamin C, niacin and manganese contribute to normal energy-yielding metabolism and to the reduction of tiredness and fatigue\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "Long term intake of amounts greater than 10mg vitamin B6 daily may lead to mild tingling and numbness\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_sucroguard"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_dricelleomegaplex",
                "Dricelle OmegaPlex (Omega-3 & 6 Fatty Acids) 120g",
                "The DriCelle process allows large doses of oils to be taken in a powder form with enhanced absorption and bioavailability. OmegaPlex provides both omega-3 and omega-6 fatty acids (as fish, linseed and borage oils).\n",
                "Fatty Acids",
                "120g",
                0,
                "Fructose, Potato Maltodextrin, Modified Tapioca Starch, Fish Oil, Borage Oil, Potassium Bicarbonate, Lemon Puree (Sulphur Dioxide), Linseed Oil, Citric Acid, Vitamin C (as Ascorbic Acid), Vitamin E (as D-Alpha Tocopheryl Acetate), Lemon Oil, Antioxidants (Ascorbic Acid, Ascorbyl Palmitate & Natural Mixed Tocopherols), Sunflower Oil, Riboflavin (as Riboflavin-5-Phosphate Sodium). ",
                "• When mixed into water it produces a pleasant tasting effervescent lemon drink therefore suitable for those that are unable or prefer not to swallow a capsule or tablet\n" +
                        "• More convenient than taking large numbers of capsules\n" +
                        "• Micellised for enhanced absorption and bio-availability\n" +
                        "• Provides fish oil from anchovies and sardines and is free from detectable PCBs and contaminants\n" +
                        "• Contains additional antioxidants vitamin E and vitamin C to enhance stability, and is vacuum packed to maintain freshness and protect the delicate oils from oxidation\n" +
                        "• Suitable for children from 2 years of age\n" +
                        "• Vitamin C contributes to normal collagen formation for the normal function of skin\n" +
                        "• Vitamin C contributes to normal psychological function\n" +
                        "• For full allergen information, scroll over label above\n",
                "One heaped teaspoon (approx. 5 grams) mixed into water or juice taken daily with food, or as professionally directed.",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Caution is advised with anti-coagulant medication\n" +
                        "Not suitable for individuals suffering from epilepsy\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_dricelleomegaplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_asc_plus",
                "ASC Plus - 90 Capsules",
                "ASC Plus contains a combination of useful key nutrients for men including, the amino acids, arginine, carnitine and taurine, together with zinc, selenium and vitamin E.",
                "Specific Nutrient Complexes",
                "90",
                0,
                "L-Arginine Hydrochloride, Vitamin E (as D-Alpha Tocopheryl Succinate), Capsule Shell (HPMC), L-Carnitine L-Tartrate L-Taurine, Zinc Citrate, Dicalcium Phosphate, Anti-caking Agents (Silicon Dioxide & Magnesium Stearate), Sodium Selenite. ",
                "• Zinc contributes to normal fertility and reproduction\n" +
                        "• Zinc contributes to the maintenance of normal testosterone levels in the blood\n" +
                        "• Zinc and selenium are antioxidants and contribute to the protection of cells from oxidative stress\n" +
                        "• Selenium has been shown to contribute to sperm formation\n" +
                        "• Zinc and selenium contribute to the normal function of the immune system\n",
                "One capsule take three times daily or as professionally directed.",
                "Not suitable for use during planned pregnancy, pregnancy, or breastfeeding.\n" +
                        "If you are under medical supervision, please consult a doctor before use.\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "Store below 25oC in a dry place away from direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_asc_plus"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_femfortemulti",
                "FemForte® Multi - 90 Capsules",
                "FemForte® Multi is a comprehensive, high potency multivitamin, mineral & antioxidant complex, particularly suited to the special requirements of women. It contains high ratios of nutrients supportive of female health.\n",
                "Multi-Nutrients",
                "90",
                0,
                "Magnesium Citrate, Capsule Shell (HPMC), Vitamin C (as Magnesium Ascorbate), Calcium Citrate, Potassium Citrate, Zinc Ascorbate, Vitamin E (as D-Alpha Tocopheryl Succinate), Vitamin B6 (as Pyridoxal-5-Phosphate), Choline Bitartrate, Iron Citrate, Pantothenic Acid (as Calcium Pantothenate), Niacin (as Nicotinamide), Modified Corn Starch, Inositol, Thiamine (as Thiamine Hydrochloride), Anti-Caking Agents (Cellulose & Magnesium Stearate), Dicalcium Phosphate, Riboflavin, Manganese Citrate, PABA (Para Amino Benzoic Acid), Olive Oil, Microcrystalline Cellulose, Green Tea Extract (Camellia sinensis Leaf), Modified Tapioca Starch, Beta Carotene, Chromium Picolinate, Vitamin A (as Retinyl Palmitate),  Acacia Gum, Sunflower Oil, Sucrose, Corn Starch, Copper Gluconate, Folic Acid, Antioxidants (Ascorbic Acid, Ascorbyl Palmitate & Natural Mixed Tocopherols), Sodium Molybdate, L-Selenomethionine, Potassium Iodide, Biotin, Sodium Borate, Vitamin B12 (as Hydroxycobalamin), Vitamin D2 (as Ergocalciferol). ",
                "• FemForte® Multi has been formulated in line with current research. It replaces FemForte I & II and contains increased levels of magnesium, iodine and vitamins B6 & D, now also contains green tea\n" +
                        "• Suitable to support women of childbearing age\n" +
                        "• Provides nutrients in their most bio-available form for optimum absorption\n" +
                        "• Hypoallergenic formulation in a capsule for easy swallowing\n" +
                        "• Vitamin B6 contributes to the regulation of hormonal activity\n" +
                        "• B vitamins, including B3, B6, and B12, vitamin C and magnesium contribute to normal energy-yielding metabolism and normal functioning of the nervous system\n" +
                        "• Vitamin D, calcium and manganese contribute to the maintenance of normal bones\n" +
                        "• Selenium and zinc contribute to the maintenance of normal hair and nails\n" +
                        "• Zinc contributes to normal fertility and reproduction\n" +
                        "• Vitamin A, C and D contribute to the normal function of the immune system\n" +
                        "• Vitamin E contributes to the protection of cells from oxidative stress\n" +
                        "• Designed by Alan Hibberd, Clinical Toxicologist\n",
                "One capsule taken three times daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use.\n" +
                        "Long term intake of amounts greater than 10mg of vitamin B6 may lead to mild tingling and numbness.\n" +
                        "This product contains iron, which if taken in excess may be harmful to very young children.\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_femfortemulti"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_femforte_balance",
                "FemForte® Balance 60 Capsules",
                "FemForte® Balance is a potent mix of botanical extracts, suitable for women during and after childbearing age. It contains high levels of red clover, providing a natural source of isoflavones genistein, daidzein, biochanin, and formononetin.\n",
                "Specific Nutrient Complexes",
                "60",
                0,
                "Red Clover Standardised Extract (Trifolium pratense Ariel Parts), Broccoli Powder (Brassica oleracea L. var italic Floret), Beetroot Extract (Beta vulgaris Root), Potato Maltodextrin, Capsule Shell (HPMC), Broccoli Standardised Extract (Brassica oleracea L. var italic Sprout), Green Tea Extract (Camellia sinensis Leaf), Corn Maltodextrin, Bulking Agent (Cellulose), Anti-Caking Agent (Magnesium Stearate), Citric Acid. ",
                "• Red clover provides a natural source of Isoflavones\n" +
                        "• Broccoli and green tea contain potent antioxidants\n" +
                        "• Suitable for women during and after childbearing age\n" +
                        "• Hypoallergenic formulation in a capsule for easy swallowing\n",
                "One capsule taken twice daily with food or as professionally directed.\n",
                "If you are under medical supervision please consult a doctor before use\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_femforte_balance"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_bioplantarum",
                "BioPlantarum Plus Sterols - 90 Capsules",
                "A specialised blend of high potency live bacteria with plant sterols to support healthy cholesterol levels.\n",
                "Specific Nutrient Complexes",
                "90",
                0,
                "Plant Sterols, Capsule Shell (HPMC), Bulking Agent (Microcrystalline Cellulose), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Lactobacillus plantarum.",
                "• High potency guaranteed - 10 billion of the scientifically researched Lactobacillus plantarum, which is acid resistant and stable in storage so you're guaranteed to get what you need\n" +
                        "• Provides the optimum level of natural plant sterols which contribute to the maintenance of normal cholesterol levels\n" +
                        "• Pure - free of unnecessary ingredients and allergens, dairy free (which many live bacteria products are not), so vegan-friendly\n" +
                        "• Trusted - BioCare®  has been the choice of professionals for over 30 years - our products are formulated by our team of nutritionists and used successfully by clinicians with their clients\n" +
                        "• Plant sterols contribute to the maintenance of normal blood cholesterol levels with a daily intake of at least 800mg plant sterols\n",
                "Three capsules taken daily with food, or as professionally directed.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle.\n" +
                        "Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken.\n" +
                        "Keep out of reach of children.\n" +
                        "If you are under medical supervision, please consult a doctor before use.\n" +
                        "Not to be taken by pregnant and breastfeeding women or children under 5 years of age.\n" +
                        "If you are taking cholesterol lowering medication seek medical advice before taking this product.\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_bioplantarum"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_ginseng",
                "Ginseng 60 Capsules",
                "A standardised extract of Panax Ginseng providing ginsenosides and flavonoids.\n",
                "Herbal",
                "60",
                0,
                "Panax Ginseng (Panax Ginseng Stem & Rhizomes), Bulking Agent (Cellulose), Capsule Shell (HPMC), Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide).  ",
                "• Suitable for vegetarians and vegans\n" +
                        "• Convenient one-a-day capsule\n" +
                        "• Helps to promote alertness\n" +
                        "• Helps to counteract fatigue\n" +
                        "• Helps to maintain good cognitive performance\n",
                "One capsule taken twice daily with food , or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_ginseng"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_femguard",
                "FemGuard® (Multinutrient for Women) 90 Caps",
                "FemGuard® is a comprehensive, high potency multinutrient designed to support female health throughout the menopause. It contains high ratios of nutrients that are supportive of female hormonal balance, bone density and cardiovascular health.\n",
                "Specific Nutrient Complexes",
                "90",
                0,
                "Magnesium Citrate, Vitamin C (as Magnesium Ascorbate), Calcium Citrate, Potassium Citrate, Capsule Shell (HPMC), Pantothenic Acid (as Calcium Pantothenate), Zinc Citrate, Vitamin E (as D-Alpha Tocopheryl Succinate), Vitamin B6 (as Pyridoxal-5-Phosphate), Choline Bitartrate, Niacin (as Nicotinamide), Bilberry  Extract (Vaccinium myrtillus Berry), Corn Starch, Inositol, Potato Maltodextrin, Thiamine (as Thiamine Hydrochloride),  Dicalcium Phosphate, Riboflavin, Manganese Citrate, Bulking Agent (Cellulose), PABA (Para Amino Benzoic Acid), Olive Oil, Anti-Caking Agent (Magnesium Stearate), Modified Tapioca Starch , Microcrystalline Cellulose , Green Tea  Extract (Camellia sinensis Leaf), Grape Seed Extract (Vitus vinifera Seed as Vitaflavan®), Beta Carotene, Copper Gluconate, Sunflower Oil, Acacia Gum, Chromium Picolinate, Citric Acid,  Vitamin A (as Retinyl Palmitate), Sucrose , Antioxidants (Natural Mixed Tocopherols, Ascorbic Acid & Ascorbyl Palmitate), Folic Acid , Potassium Iodide, L-Selenomethionine, Sodium Molybdate, Sodium Borate, Biotin, Vitamin B12 (as Hydroxycobalamin), Vitamin K (as Phylloquionone), Vitamin D (as Ergocalciferol). ",
                "• Suitable for women prior to, during and after the menopause\n" +
                        "• Provides nutrients in their most bioavailable form for optimum absorption\n" +
                        "• B vitamins, including B3, B6, and B12, vitamin C and magnesium contribute to normal energy-yielding metabolism and normal functioning of the nervous system\n" +
                        "• Pantothenic acid is essential for metabolism of steroid hormones\n" +
                        "• Vitamin D, calcium and manganese contribute to the maintenance of normal bones\n" +
                        "• Selenium and zinc contribute to the maintenance of normal hair and nails\n" +
                        "• Vitamins A, C and D contribute to the normal function of the immune system\n" +
                        "• Vitamin E contributes to the protection of cells from oxidative stress\n" +
                        "• Bilberry, grapeseed and green tea are a good source of flavonoids, which have antioxidant properties\n",
                "One capsule taken three times daily with food, or as professionally directed.\n",
                "Long term intake of amounts greater than 10mg vitamin B6 daily may lead to mild tingling and numbness\n" +
                        "Long term intakes of amounts greater than 25mg of zinc daily may lead to anaemia\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_femguard"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_childrensmindlinxpowder",
                "Children's Mindlinx® Powder 60g",
                "Children's Mindlinx® is a high potency live bacteria supplement with added glutamine particularly useful for children. Available in powder or vegetable capsules.\n",
                "Probiotics",
                "60g",
                0,
                "L-Glutamine, Fructooligosaccharides (F.O.S.), Potato Maltodextrin, Apricot Powder, Lactobacillus acidophilus, Lactobacillus rhamnosus, Bifidobacterium bifidum, Bifidobacterium lactis.  ",
                "• High potency powder that can easily be mixed into food or liquid, ideal for those unable or who prefer not to take capsules or tablets\n" +
                        "• Vacuum packed to maintain stability\n" +
                        "• Dairy free\n" +
                        "• Glutamine is the most abundant free amino acid in human muscle and plasma\n" +
                        "• Mindlinx® utilises transient bacterial strains as well as the LAB<sup>4</sup> complex of proprietary strains\n" +
                        "• LAB<sup>4</sup> has been tested extensively\n" +
                        "• An encapsulated version of Mindlinx® is also available\n",
                "Half a teaspoon (approximately two grams) taken daily with food or as professionally directed.\n" +
                        "Suitable from two years of age.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Vacuum sealed for stability\n" +
                        "Refrigerate below 4&deg;C and avoid direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_childrensmindlinxpowder"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_mindlinx",
                "Mindlinx 60 Caps",
                "Mindlinx® is a high potency live bacteria supplement with added glutamine which is particularly useful for children. Available in powder or vegetable capsules\n",
                "Probiotics",
                "60",
                0,
                "L-Glutamine, Capsule Shell (HPMC), Lactobacillus acidophilus, Lactobacillus rhamnosus, Anti-Caking Agents (Silicon Dioxide &  Magnesium Stearate),  Bifidobacterium bifidum, Bifidobacterium lactis. ",
                "• Contains Lactobacillus Rhamnosus\n" +
                        "• High potency capsules that can easily be opened and mixed into food or liquid\n" +
                        "• Vacuum packed to maintain stability\n" +
                        "• Dairy free\n" +
                        "• Glutamine is the most abundant free amino acid in human muscle and plasma\n" +
                        "• Mindlinx® utilises transient bacterial strains as well as the LAB<sup>4</sup> complex of proprietary strains\n" +
                        "• LAB<sup>4</sup> has been tested extensively\n" +
                        "• A powder version of Mindlinx® is also available\n",
                "One capsule taken twice daily with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Refrigerate below 4&deg;C and avoid direct sunlight and heat\n" +
                        "Vacuum sealed for stability\n",
                ImageRetriever.createUrl("biocare_mindlinx"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_glucosamine_msm_complex",
                "Glucosamine MSM Complex",
                "Glucosamine MSM Complex combines glucosamine, MSM and chondroitin in a potent and convenient tablet form\n",
                "Specific Nutrient Complexes",
                "90",
                0,
                "Glucosamine Hydrochloride*, Methyl Sulphonyl Methane (MSM), Bulking Agent (Microcrystalline Cellulose), Chondroitin Sulphate (Fish, Molluscs), Anti-Caking Agent (Ascorbyl Palmitate), Silicon Dioxide. *From the fungus Aspergillus niger ",
                "• Glucosamine MSM Complex provides an optimum level of glucosamine, at 1500mg, in the absorbable and well tolerated hydrochloride form\n" +
                        "• Glucosamine and chondroitin are natural structural components of connective tissue such as cartilage, tendons or bones\n" +
                        "• Methyl Sulphonyl Methane (MSM) is a natural substance that is found in sulphur bearing foods and naturally occurs in the human body\n" +
                        "• 30 days' supply at 3 tablets per day\n",
                "One tablet taken three times daily with food, or as professionally directed.",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision please consult a doctor before use\n" +
                        "Glucosamine 1500mg provided by Glucosamine Hydrochloride 1842mg\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat.\n",
                ImageRetriever.createUrl("biocare_glucosamine_msm_complex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_oneadayplus",
                "One A Day Plus (Multinutrient) 30 Tabs",
                "One A Day Plus provides all of the benefits of our One A Day supplement but with additional co-enzyme Q10 and Vitaflavan® grapeseed extract. All key nutrients are provided in their most bioavailable forms.\n",
                "Multi-Nutrients",
                "30 & 60 & 90",
                0,
                "Bulking Agents (Dicalcium Phosphate & Microcrystalline Cellulose), Vitamin C (as Magnesium Ascorbate), Calcium Citrate, Niacin (as Nicotinamide), Vitamin E (as D-Alpha Tocopheryl Succinate), Pantothenic Acid (as Calcium Pantothenate), Choline Bitartrate, Vitamin B6 (as Pyridoxine Hydrochloride), Magnesium Citrate, Zinc Citrate, Acacia Gum, Thiamine (as Thiamine Hydrochloride), Tablet Coating (Glazing Agents [Hydroxymethylcellulose & Glycerin], & Colours [Titanium Dioxide & Riboflavin]), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Manganese Ascorbate, Cellulose, Inositol, Riboflavin, Para Amino Benzoic Acid, Iron (as Ferrous Fumarate), Bilberry Extract (Vaccinium myrtillus Berry), Cross Linked Carboxymethylcellulose, CoEnzyme Q10, Sodium Alginate, Grapeseed Extract (Vitus vinifera Seed as Vitaflavan®), Green Tea (Camellia sinensis Leaf), Modified Tapioca Starch, Lutein (Tagetes erecta flower), Sodium Borate, Alginate, Soy Protein, Pea Starch, Beta Carotene, Vitamin A (as Retinyl Palmitate), Sodium Molybdate, Chromium Picolinate, Sunflower Oil, Sucrose, Corn Starch, Folic Acid, Biotin, Sodium Selenite, Potassium Iodide, Vitamin B12 (as Hydroxycobalamin), Natural Mixed Tocopherols, Vitamin D2 (as Ergocalciferol).  ",
                "• Optimal bioavailabilty and formulated for people with allergies and intolerances in mind\n" +
                        "• Contains lutein, bilberry, beta carotene, green tea, grapeseed extract and co-enzyme Q10\n" +
                        "• Vitamin C contributes to the protection of cells from oxidative stress\n" +
                        "• Vitamin B3 contributes to the normal functioning of the nervous system, psychological function, and normal energy-yielding metabolism\n" +
                        "• Provides 7mg of elemental iron\n" +
                        "• Vitaflavan® is a standardised, premium, high-potency source of grapeseed extract which is an excellent source of proanthocyanidins\n" +
                        "• Provides vitamin C in a low acidity form as magnesium ascorbate\n" +
                        "• Vitamin C contributes to the normal function of the immune system and the normal collagen formation for the normal function of bones, blood vessels, cartilage, gums, skin & teeth\n" +
                        "• Contains vitamin D2 - suitable for vegans\n" +
                        "• Vitamin D contributes to the normal function of the immune system and to normal blood calcium levels\n" +
                        "• B vitamins contribute to normal energy-yielding metabolism\n" +
                        "• Contains yeast-free selenium and natural vitamin E\n" +
                        "• Convenient one a day formula\n" +
                        "• NB: NOT FOR SALE IN THE UNITED STATES\n",
                "One tablet taken daily with food or as professionally directed.\n",
                "This product contains iron, which, if taken in excess, may be harmful to very young children. Keep out of reach of children\n" +
                        "Long term intake of amounts greater than 10mg vitamin B6 daily may lead to mild tingling and numbness\n" +
                        "Long term intake of amounts greater than 4mg of manganese daily may lead to muscle pain and fatigue\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_oneadayplus"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_bioacidophiluspowder",
                "BioAcidophilus Powder 60g",
                "BioAcidophilus Powder is a high potency blend of the LAB4 complex of live probiotic cells in powder form. Each daily intake (2g) contains 20 billion viable bacteria.\n",
                "Probiotics",
                "60g",
                0,
                "Fructooligosaccharides (Chichorium intybus Root), Potato Maltodextrin, Fructose, Apricot Powder, Lactobacillus acidophilus, Bifidobacterium bifidum & Bifidobacterium lactis. ",
                "• A powdered probiotic, useful for those needing a higher intake but in powder form\n" +
                        "• Guaranteed 20 billion potency per daily intake to end of shelf life if stored correctly\n" +
                        "• Contains the unique, extensively tested LAB<sup>4 </sup>complex of friendly bacteria\n" +
                        "• Human compatible strains that are bile and acid tolerant with high adherence ability and a proven history of safe use in humans\n" +
                        "• Hypoallergenic and dairy-free\n" +
                        "• Vacuum packed and microencapsulated to maintain the stability of these fragile organisms\n" +
                        "• Contains Fructooligosaccharides (F.O.S.) derived from chicory\n",
                "Two grams taken daily (approximately half a teaspoon) in water, juice or milk, with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Vacuum sealed for stability\n" +
                        "Refrigerate below 4&deg;C and avoid direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_bioacidophiluspowder"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_childrensomegaberry",
                "Children's OmegaBerry® (Omega-3 fish oil) 150ml",
                "Children's OmegaBerry® is an emulsified fish oil with tropical fruit & berry concentrates, making it an optimally absorbed and great tasting source of omega-3 fatty acids for the whole family.\n",
                "Fatty Acids",
                "150ml",
                0,
                "Fish Oil Concentrate, Pineapple Juice Concentrate, Fructose, Water, Wild Berry Concentrate (Chokeberry, Apple, Elderberry, Blueberry & Blackcurrant), Modified Corn Starch, Mango Puree, Orange Juice Concentrate, Banana Puree, Pineapple Flavour, Antioxidants (Natural Mixed Tocopherols, Citric Acid & Ascorbic Acid), Acacia Gum, Sunflower Oil, Vanilla Flavour, Xanthan Gum, Preservative (Potassium Sorbate).  ",
                "• Contains blackcurrants, blueberries, chokeberries and elderberries which are a good source of proanthocyanadins\n" +
                        "• The liquid form makes it especially suitable for children and those who have difficulty or prefer not to swallow capsules\n" +
                        "• Emulsified using our unique BioCare® BioMulsion® process, dramatically increasing the bio-availability of the oils\n" +
                        "• Naturally concentrated and pre-digested using our patented NEO-3™ process, utilising lipase enzymes\n" +
                        "• Utilises our 'Multox' antioxidant system for enhanced stability\n" +
                        "• Provides fish oil from anchovies and sardines and is free from detectable PCBs and contaminants\n" +
                        "• Great tasting fish oil liquid for children\n" +
                        "• Palatable and easy to mix into water or fruit juices\n" +
                        "• For full allergen information, scroll over label above\n",
                "For children between 3 - 5 years, half a teaspoon daily (approx 2.5ml), and for children 5 years and over, one teaspoon daily (approx 5ml), or as professionally directed. Shake well before use.\n" +
                        "For adults one teaspoon (approx 5ml) taken twice daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Caution is advised with anti-coagulant medication\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Vacuum sealed for stability\n" +
                        "Once opened, refrigerate and consume within 4 months\n",
                ImageRetriever.createUrl("biocare_childrensomegaberry"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_biomulsion_omegaberry",
                "BioMulsion OmegaBerry® (Omega-3 fish oil) 300ml",
                "BioMulsion Omegaberry® is an emulsified, high potency fish oil liquid in a  berry and fruit base. As well as providing the fatty acids EPA and DHA it provides mixed berry concentrate making a tasty source of fatty acids.\n",
                "Fatty Acids",
                "300ml",
                0,
                "Fish Oil Concentrate, Pineapple Juice Concentrate, Fructose, Water, Wild Berry Concentrate (Chokeberry, Apple, Elderberry, Blueberry & Blackcurrant), Modified Corn Starch, Mango Puree, Orange Juice Concentrate, Banana Puree, Pineapple Flavour, Antioxidants (Natural Mixed Tocopherols, Citric Acid & Ascorbic Acid), Acacia Gum, Sunflower Oil, Vanilla Flavour, Xanthan Gum, Preservative (Potassium Sorbate). ",
                "• Contains blackcurrants, blueberries, chokeberries and elderberries which provide proanthocyanadins\n" +
                        "• Liquid form is especially suitable for those who have difficulty swallowing capsules\n" +
                        "• DHA contributes to maintenance of normal brain function and vision\n" +
                        "• EPA and DHA contribute to the normal function of the heart \n" +
                        "• Maternal intake of Docosahexaenoic acid (DHA) contributes to the normal brain and eye development of the foetus and breastfed infants\n" +
                        "• Naturally concentrated and pre-digested using our patented NEO-3™ process, utilising lipase enzymes\n" +
                        "• Emulsified using our unique BioCare® BioMulsion® process, dramatically increasing the bio-availability of the oils\n" +
                        "• Utilises our 'Multox' antioxidant system for enhanced stability\n" +
                        "• Provides fish oil from anchovies and sardines and is free from detectable PCBs and contaminants\n" +
                        "• For full allergen information, scroll over label above\n",
                "One teaspoon (approximately 5ml) taken twice daily with food, or as professionally directed.\n" +
                        "Shake well before use.\n",
                "Caution is advised with anti-coagulant medication\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Vacuum sealed for stability\n" +
                        "Once opened, refrigerate and consume within 4 months\n",
                ImageRetriever.createUrl("biocare_biomulsion_omegaberry"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_bioantioxidant2000",
                "BioAntioxidant 2000 30 Capsules",
                "BioAntioxidant 2000 is the broadest spectrum antioxidant product in the BioCare® range. It combines phytonutrients from 14 plant extracts along with coenzyme Q10, alpha lipoic acid and  beta 1-3 glucans.\n",
                "Antioxidants",
                "30",
                0,
                "Modified Corn Starch, Capsule Shell (Hydroxypropyl Methylcellulose), Zinc Ascorbate, Olive Oil, Acerola Cherry (Malpighia glabra Berries), Maize Maltodextrin, Bulking Agent (Cellulose), Rutin (Sophorae japonica Flower), Hesperidin (Citrus sinensis Peel),  Beta Glucans, Quercetin (Sophorae japonica Flower), Coenzyme Q10, Alpha Lipoic Acid,  Magnesium Gluconate, Gallic Acid, Citrus Bioflavonoids (Citrus sinensis Peel), Green Tea Extract (Camellia sinensis Leaf), Grapeseed Extract (Vitus vinifera Seed as Vitaflavan®), Ginkgo Biloba Extract (Ginkgo biloba Leaf), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Lutein, Soya Oil, Modified Tapioca Starch, Bilberry Extract (Vaccinium myrtillus Berry), Limonene Powder (Citrus sinensis Peel), Lycopene Powder (Lycopersicon esculentum Fruit), Antioxidants (Ascorbic Acid, Ascorbyl Palmitate & Natural Mixed Tocopherols), Beta Carotene, Sunflower Oil.  ",
                "• Contains zinc and vitamin C which are antioxidants that  contribute to the protection of cells from oxidative damage and  which contribute to the normal function of the immune system\n" +
                        "• Provides flavonoids and carotenoids\n" +
                        "• Contains standardised, high-potency green tea and ginkgo extracts.\n" +
                        "• Vitaflavan® is a standardised, high potency, premium quality grapeseed extract which is an excellent source of purified, bio-available proanthocyanidins\n" +
                        "• Green tea, ginkgo biloba and grapeseed extract contribute to the protection of DNA, protein and lipids from oxidative damage\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_bioantioxidant2000"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_glcomplex",
                "GI Complex 165g",
                "GI Complex is a unique, high strength powder containing l-glutamine, N-acetyl glucosamine, whey and probiotics along with vitamins A, C and E.\n",
                "Gastrointestinals",
                "165g",
                0,
                "Whey Protein Concentrate (Milk), L-Glutamine, Fructooligosaccharides (F.O.S.),  N-Acetyl Glucosamine (Crustaceans), Ginger Powder (Zingiber Officinalis root), Vitamin C (as Magnesium Ascorbate), Zinc Citrate, Vanilla Flavour, Lactobacillus acidophilus, Silicon Dioxide, Evening Primrose Oil, Modified Tapioca Starch, Potato Maltodextrin, Vitamin E (as D-Alpha Tocopheryl Acetate), Lactobacillus salivarius, Acacia Gum, Sucrose, Corn Starch, Bifidobacterium bifidum, Bifidobacterium lactis, Vitamin A (as Retinyl Acetate), Antioxidants (Ascorbic Acid, Ascorbyl Palmitate & Natural Mixed Tocopherols), Corn Oil, Sunflower Oil, DL-Alpha Tocopherol. ",
                "• Easy to use powder form that can be mixed with water\n" +
                        "• Contains vitamin A that contributes to the maintenance of normal mucous membranes and the function of the immune system\n" +
                        "• Contains probiotics Lactobacillus acidophilus, salivarius, Bifidobacterium bifidum and lactis\n" +
                        "• Contains fructooligosaccharides (F.O.S.) derived from chicory\n" +
                        "• Contains whey protein\n" +
                        "• Zinc contributes to the protection of cells from oxidative stress\n" +
                        "• For full allergen information, scroll over label above\n",
                "Two teaspoons (approximately 5g) mixed well in water and taken daily with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product contains vitamin A. Do not take if you are pregnant or likely to become pregnant except on the advice of a doctor or ante-natal clinic\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Refrigerate below 4&deg;C and avoid direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_glcomplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_slipperyelmintensive",
                "Slippery Elm Intensive 75g",
                "A high strength, intensive powder containing slippery elm with gamma oryzanol, licorice, marshmallow & aloe vera extracts.\n",
                "Gastrointestinals",
                "75g",
                0,
                "Gamma Oryzanol (Oryza sativa Germ and Husk), Cinnamon (Cinnamomum cassia Bark), Deglycyrrated Licorice (Glycyrrhizia glabra Root), Konjac Fibre, Slippery Elm (Ulmus fulva Bark), Aloe Vera Extract (Aloe Barbadensis Inner leaf fillet), Marshmallow (Althea officinalis Root), Corn Maltodextrin, Zinc Gluconate. ",
                "• Combination of slippery elm, marshmallow, licorice, aloe and gamma oryzanol\n" +
                        "• High strength, intensive powder for maximum efficacy and flexibility\n" +
                        "• Zinc has a role in the process of cell division and the protection of cells from oxidative stress\n" +
                        "• Marshmallow may support gastrointestinal health\n" +
                        "• Slippery elm soothes the digestive tract and is a source of mucilage which supports the mucous membranes\n" +
                        "• Licorice helps support digestion and particularly helps to maintain balance and comfort in the digestive systems of people with sensitive digestion\n",
                "One heaped teaspoon (approximately 2.5g) mixed well in water and taken daily before food, or as professionally directed.\n" +
                        "Always take with plenty of water.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_slipperyelmintensive"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_microcell_coq10200",
                "Microcell CoQ10 200 30 Capsules",
                "A high potency combination of 200mg co-enzyme Q10, olive oil and vitamin E, micellised to significantly increase absorption and bioavailability.\n",
                "Fatty Acids",
                "30",
                0,
                "CoEnzyme Q10, Capsule Shell [HPMC & Colours (Red Iron Oxide & Titanium Dioxide)], Olive Oil Powder (Olea europaea Fruit), Corn Starch, Potato Maltodextrin, Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Vitamin E (as D-Alpha Tocopheryl Acetate), Antioxidants (Ascorbic Acid, Ascorbyl Palmitate & Natural Mixed Tocopherols), Acacia Gum, Sunflower Oil. ",
                "• Optimum dose Co-Enzyme Q10, providing 200mg per day\n" +
                        "• Micellised (to make water soluble) for superior absorption\n" +
                        "• MicroCell® nutritional oils are encapsulated in chlorophyll vegetable capsules to maintain stability\n",
                "One capsule taken daily with food, or as professionally directed.\n",
                "Caution is advised with anti-coagulant medication\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_microcell_coq10200"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_nutrisorbliquidmethylfolguard",
                "Nutrisorb® Liquid Methyl FolGuard® 15ml",
                "Nutrisorb®  Methyl FolGuard® is a high potency liquid supplement of optimally bioavailable, metabolically active forms of vitamin B12 (as methylcobalamin) and folate (as methylfolate) to support healthy methylation.\n",
                "Specific Nutrient Complexes",
                "15",
                0,
                "Purified Water, Folate (as Quatrefolic® [(6S)-5-Methyltetrahydrofolic acid glucosamine salt]), Vitamin B12 (as Methylcobalamin), Citric Acid, Preservative (Potassium Sorbate). ",
                "• Nutrisorb® Methyl FolGuard®  is a high potency liquid supplement of optimum potency, naturally active forms of vitamin B12 (as methylcobalamin) and folate (as methylfolate), providing 100mcg each per drop\n" +
                        "• Methylfolate or 5-MTHF is the natural form of folate found in food such as leafy green vegetables and is already active and ready for the body to use\n" +
                        "• Vitamin B12 and folate support methylation, contribute to red blood cell formation, normal psychological function and energy metabolism\n" +
                        "• Taking a supplement containing 400mcg of folic acid is recommended prior to and during early pregnancy as it contributes to normal maternal tissue growth\n" +
                        "• Easy to absorb and use liquid form. Quatrefolic®  is the most advanced form of folate supplementation available, the most advanced form of folate supplementation available, with optimum stability and bioavailability\n" +
                        "• Flexible - combine with other nutrients and take exactly what you need\n" +
                        "• Well suited to individuals who have difficulty swallowing tablets or capsules, such as children or the elderly, or those with digestive and absorption difficulties\n" +
                        "• Suitable for vegetarians and vegans\n" +
                        "• Approx. 400 drops per bottle, 100 days' supply at 4 drops\n",
                "Four drops taken daily with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_nutrisorbliquidmethylfolguard"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_antioxidantcomplex",
                "Antioxidant Complex - 30 Capsules",
                "Antioxidant Complex is a potent and synergistic combination of natural plant extracts including flavonoids and carotenoids, with alpha lipoic acid and vitamin C, providing optimum antioxidant support.\n",
                "Antioxidants",
                "30",
                0,
                "Natural Mixed Carotenoids, Capsule Shell (HPMC), Turmeric (Curcuma longa Root), Quercetin (Sophorae japonica Flower), Alpha Lipoic Acid, Vitamin C (as Ascorbic Acid),  Grapeseed Extract (Vitaflavan® as Vitus vinifera Seed), Green Tea Extract (Camellia sinensis Leaf), Modified Corn Starch, Bilberry Extract (Vaccinium myrtillus Fruit), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate), Corn Starch, Lycopene, Glucose Syrup, Black Pepper Extract (BioPerine® as Piper nigrum Fruit), Antioxidants (Sodium Ascorbate & DL-Alpha Tocopherol). ",
                "• Antioxidant Complex is a potent and synergistic combination of natural plant extracts including flavonoids and carotenoids, with alpha lipoic acid and vitamin C, providing optimum antioxidant support\n" +
                        "• Antioxidant Complex has increased levels of green tea, turmeric and Vitaflavan® grapeseed extract, alongside other potent phytonutrients such as quercetin, lycopene, lutein and high strength bilberry extract\n" +
                        "• Curcumin (turmeric) helps to maintain the immune system and has antioxidant properties\n" +
                        "• Grapeseed extract may support venous circulation, maintain healthy blood vessels and capillary integrity\n" +
                        "• Grapeseed extract is an antioxidant that protects against free radicals and maintains normal cell health and function\n" +
                        "• Vitaflavan® is a highly purified source of antioxidants from white grape seed with superior potency and absorption. It provides 850 ORAC units per 50mg\n" +
                        "• Vitamin C contributes to the protection of cells from oxidative stress and to the maintenance of the immune system\n" +
                        "• BioPerine®  enhances the bioavailability of a wide range of antioxidants including curcumin, quercetin and carotenoids\n" +
                        "• New Antioxidant Complex now offers the convenience of one capsule dose per day rather than 3 and is an excellent adjunct to a multivitamin and mineral formulation\n" +
                        "• Suitable for vegetarians and vegans. 30 days' supply at 1 capsule per day\n",
                "One capsule taken daily with food, or as professionally directed\n",
                "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "Caution is advised with anti-coagulant medication\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Keep out of reach of children\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_antioxidantcomplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_biod",
                "Bio-D (400iu) Vitamin D Liquid 15ml",
                "Bio-D is a liquid vitamin D supplement providing 400iu of vitamin D3, cholecalciferol per daily dose in a simple olive oil base.",
                "Vitamins",
                "15ml",
                0,
                "Extra Virgin Olive Oil, Medium Chain Triglycerides, Vitamin D3 (Cholecalciferol), Antioxidant (DL-Alpha Tocopherol) ",
                "• Provides liquid vitamin D to facilitate absorption\n" +
                        "• Ideal for those who have difficulty swallowing capsules and tablets\n" +
                        "• Can be taken sublingually (under the tongue) for those with gastrointestinal difficulties\n" +
                        "• Ideal for individuals with malabsorption\n" +
                        "• Detergent and surfactant free\n" +
                        "• Vitamin D contributes to normal absorption/utilisation of calcium and phosphorus\n" +
                        "• Vitamin D contributes to normal blood calcium levels\n" +
                        "• Vitamin D contributes to the maintenance of normal bones and teeth\n" +
                        "• Vitamin D contributes to the maintenance of normal muscle function\n" +
                        "• Vitamin D contributes to the normal function of the immune system\n" +
                        "• Vitamin D has a role in the process of cell division\n",
                "Four drops taken daily in water with food, or directly under the tongue, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_biod"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocarre_lglutaminepowder",
                "L-Glutamine Powder 200g",
                "Glutamine is the most prevalent amino acid in the human body. It has a wide range of functions. \n",
                "Amino Acids",
                "200g",
                0,
                "L-glutamine ",
                "• Provided as a 200g pure free flowing powder allowing flexibility of dosage\n",
                "Take one teaspoon (approximately 5 grams) per day before food, or as professionally directed.\n",
                "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocarre_lglutaminepowder"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_nutrisorbliquidbiomulsiona",
                "Nutrisorb® Liquid BioMulsion® A (5000iu) 10ml",
                "Nutrisorb® BioMulsion® A is a potent liquid vitamin A supplement that is emulsified for maximum absorption and superior bioavailability.  It provides 5000iu per daily intake in a pleasant lemon flavoured liquid.\n",
                "Vitamins",
                "10ml",
                0,
                "Purified Water, Fructose, Acacia Gum, Vitamin A (as Retinyl Palmitate with DL-Alpha Tocopherol), Natural Lemon Oil, Preservatives (Citric Acid & Potassium Sorbate). ",
                "• Emulsified for maximum absorption and superior bio-availability\n" +
                        "• Liquid fruit puree base for delicious, effective and flexible use\n" +
                        "• Maximum strength, maximum convenience in a few tiny drops\n" +
                        "• Ideal for those who wish to avoid tablets or capsules\n" +
                        "• High potency vitamin A 5000i.u. per drop.\n" +
                        "• Vitamin A contributes to normal iron metabolism\n" +
                        "• Vitamin A contributes to the maintenance of normal mucous membranes\n" +
                        "• Vitamin A contributes to the maintenance of normal skin and vision\n" +
                        "• Vitamin A contributes to the normal function of the immune system\n" +
                        "• Vitamin A has a role in the process of cell specialisation\n",
                "One drop taken daily in water with food, or directly under the tongue, or as professionally directed. Shake well before use.\n",
                "This product contains vitamin A. Do not take if you are pregnant or planning pregnancy except on the advice of a doctor or clinic\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Refrigerate after opening and consume within 6 months\n",
                ImageRetriever.createUrl("biocare_nutrisorbliquidbiomulsiona"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_nutrisorbliquidbiomulsiond",
                "Nutrisorb® Liquid BioMulsion® D (1000iu) 10ml",
                "Nutrisorb® Liquid BioMulsion® D is a potent vitamin D supplement that is emulsified for maximum absorption and superior bioavailability.  It provides 1000iu vitamin D3 per daily intake in a pleasant lemon flavoured liquid.\n",
                "Vitamins",
                "10ml",
                0,
                "Purified Water, Fructose, Acacia Gum, Extra Virgin Olive Oil, Medium Chain Triglycerides, Natural Lemon Oil, Preservatives (Citric Acid & Potassium Sorbate), Vitamin D3 (as Cholecalciferol), Antioxidants (DL-Alpha Tocopherol & Natural Mixed Tocopherols), Sunflower Oil. ",
                "• Vitamin D is an important overall health support\n" +
                        "• Emulsified for maximum absorption and superior bio-availability\n" +
                        "• Liquid fruit puree base for delicious, effective and flexible use\n" +
                        "• Maximum strength, maximum convenience one drop\n" +
                        "• Ideal for those who wish to avoid tablets or capsules\n" +
                        "• High potency vitamin D3 (cholecalciferol) 1000i.u. per daily intake\n" +
                        "• Vitamin D contributes to normal absorption and utilisation of calcium and phosphorus\n" +
                        "• Vitamin D contributes to normal blood calcium levels\n" +
                        "• Vitamin D contributes to the maintenance of normal bones and teeth\n" +
                        "• Vitamin D contributes to the maintenance of normal muscle function\n" +
                        "• Vitamin D contributes to the normal function of the immune system\n" +
                        "• Vitamin D has a role in the process of cell division\n",
                "One drop taken daily in water with food, or directly under the tongue, or as professionally directed. Shake well before use.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Refrigerate after opening and consume within 6 months\n",
                ImageRetriever.createUrl("biocare_nutrisorbliquidbiomulsiond"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_bioacidophilusforteplus",
                "BioAcidophilus Forte Plus 30 Caps",
                "BioAcidophilus Forte Plus contains 75 billion live probiotic cells per capsule, one of the strongest available. It also includes strains of the LAB4 complex. Suitable for those who wish to use a premium strength product.\n",
                "Probiotics",
                "30",
                0,
                "Lactobacillus acidophilus, Lactobacillus salivarius, Capsule Shell (HPMC), Bulking Agent (Cellulose) Bifidobacterium bifidum & Bifidobacterium lactis, Fructooligosaccharides (Cichorium intybus Root), Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate).   ",
                "• Optimum professional potency in a capsule, delivering a guaranteed 75 billion cells per daily intake to end of shelf life if stored correctly\n" +
                        "• Contains the unique, extensively tested LAB<sup>4</sup> complex of friendly bacteria\n" +
                        "• Human compatible strains that are bile and acid tolerant with high adherence ability and a proven history of safe use in humans\n" +
                        "• Hypoallergenic and dairy-free\n" +
                        "• Vacuum packed and microencapsulated to maintain the stability of these fragile organisms\n" +
                        "• Contains Fructooligosaccharides (F.O.S.) derived from chicory\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Vacuum sealed for stability\n" +
                        "Refrigerate below 4&deg;C and avoid direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_bioacidophilusforteplus"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_eveningprimroseoil",
                "Evening Primrose Oil 30 Capsules",
                "Evening primrose oil is a natural source of gamma linolenic acid (GLA) an omega-6 fatty acid. Each capsule contains over 1000mg of pure evening primrose oil with 10mg of antioxidant vitamin E and can be used to support hormonal balance & skin health.\n",
                "Fatty Acids",
                "30",
                0,
                "Evening Primrose Oil, Capsule Shell (Modified Maize Starch, Glycerol, Gelling Agent (Carrageenan), Acidity Regulator (Sodium Carbonate)), Vitamin E (as DL-Alpha Tocopheryl Acetate), Triglcyerides, Sunflower Lecithin.  ",
                "• Convenient one-a-day capsule\n" +
                        "• High potency source of GLA providing 9.5% per capsule\n" +
                        "• Evening primrose oil provides Omega-6 series essential fatty acids\n" +
                        "• Contains antioxidant vitamin E which contributes to the protection of cells from oxidative stress and improves product stability\n" +
                        "• Evening Primrose oil may help support skin health\n" +
                        "• Evening Primrose oil may help support hormonal balance\n" +
                        "• Evening Primrose oil ensures an intake of polyunsaturated fatty acids that support a normal, healthy attitude during the menstrual cycle\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Not suitable for individuals suffering from epilepsy\n" +
                        "Caution is advised when taking anticoagulant medication\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_eveningprimroseoil"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_eradicidinforte",
                "Eradicidin Forte® 90 Tablets",
                "Eradicidin Forte® is a unique combination of garlic, oregano, cinnamon and clove in a time release tablet form. Oregano and cinnamon  help contribute to digestive function.\n",
                "Gastrointestinals",
                "90",
                0,
                "Bulking Agents (HPMC, Dicalcium Phosphate & Cellulose), Freeze Dried Garlic (Allium sativum Clove), Modified Tapioca Starch, Potato Maltodextrin, Oregano Oil Powder (Origanum vulgare Leaf), Clove (Eugenia carophyllus Fruit), Tablet Coating ([Glazing Agent: HPMC & Glycerin] & Colour [Titanium Dioxide]), Cinnamon Oil Powder (Cinnamomum zeylanicum Bark), Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide), Acacia Gum, Antioxidant (Natural Mixed Tocopherols), Sunflower Oil. ",
                "• Eradicidin Forte® uses freeze-dried and emulsified oils to maintain freshness and stability\n" +
                        "• Eradicidin Forte® includes plant fibres and mineral compounds to form a natural time release tablet ensuring controlled delivery of active phytonutrients to exactly where they can deliver maximum benefits\n" +
                        "• Garlic has antibacterial effects\n" +
                        "• Cinnamon helps maintain normal stomach gas and stomach comfort\n" +
                        "• Oregano has traditionally been used to facilitate the digestion\n",
                "One tablet taken three times a daily with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "Caution is advised for individuals with colitis, gastritis or ulcerative conditions of the stomach/intestine\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_eradicidinforte"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_psylliumintensive",
                "Psyllium Intensive (with Probiotic & Prune) 100g",
                "Psyllium Intensive is a potent powder combining BioCare's proprietary LAB4 bacteria with psyllium husk fibre and fructooligosaccharides (F.O.S.). Also includes prune, burdock and apple pectin. Mixes easily with water to make a pleasant tasting drink.\n",
                "Gastrointestinals",
                "100g",
                0,
                "Psyllium Husk (Plantago ovata Husks), Fructooligosaccharides (Cichorium intybus Root), Inulin (Cichorium intybus Root), Corn Maltodextrin, Prune Juice Powder (Prunus domestica Fruit), Burdock (Arctium lappa Root), Pectin (Malus sp. Fruit), Citric Acid, Lactobacillus acidophilus, Bifidobacterium bifidum & Bifidobacterium lactis.  ",
                "• In powder form that mixes with water to make an easy to take drink\n" +
                        "• Contains our proprietary LAB<sup>4</sup> bacterial strains\n" +
                        "• Psyllium husk contributes to intestinal transit and intestinal function and helps to maintain a healthy bowel\n" +
                        "• Burdock helps to support digestion\n" +
                        "• F.O.S. is a natural fibre that occurs in unprocessed fruits and vegetables\n",
                "One heaped scoop (approx 10g) mixed into 200ml water and taken daily with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 4&deg;C in a dry place away from direct sunlight and heat\n" +
                        "This product contains a silica gel bag to absorb moisture.\n",
                ImageRetriever.createUrl("biocare_psylliumintensive"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_artichoke_comp",
                "Artichoke Complex",
                "Artichoke Complex combines artichoke with turmeric, dandelion and burdock.\n",
                "Specific Nutrient Complexes",
                "90",
                0,
                "Artichoke Powder (Cynara scolymus Leaf), Turmeric Powder (Curcuma longa Root), Capsule Shell (HPMC), Bulking Agent (Cellulose), Dandelion (Taraxacum officinalis Root), Burdock Powder (Arctium lappa Root) , Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide).  ",
                "• Artichoke is a natural source of cynarin and flavonoids like luteolin\n" +
                        "• Artichoke may help maintain a healthy balance of blood lipids\n" +
                        "• Cynarin from artichoke may help support the gallbladder and the normal function of bile, excreting large toxins from the body as part of a healthy detoxification process\n" +
                        "• Turmeric is a potent antioxidant and helps maintain the normal action of elimination pathways in the liver\n",
                "One capsule taken three times daily with food, or as professionally directed.\n",
                "Not suitable for use during planned pregnancy, pregnancy, or breastfeeding\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_artichoke_comp"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_curcumin_plus",
                "MicroCell® Curcumin Plus",
                "MicroCell® Curcumin Plus is a combination of curcumin with ginger, ursolic acid from sage and pine bark extract. These potent phytonutrients are presented in a micellised form for maximum absorption and efficacy.\n",
                "Antioxidants",
                "90",
                0,
                "Bulking Agent (Cellulose), Curcumin Powder (Curcuma longa Root), Capsule Shell (HPMC), Sage Extract (Salvia officinalis Leaf), Ginger Extract (Zingiber officinalis Root) Corn Starch, Pine Bark Extract (Pinus pinaster Bark as Oligopin®), Potato Maltodextrin, Anti-Caking Agents (Silicon Dioxide & Magnesium Stearate). ",
                "• Micellised for enhanced bio-availability\n" +
                        "• Curcumin may help to maintain the efficacy of the immune system and has antioxidant properties\n" +
                        "• Sage and ginger may support the normal function of the immune system\n",
                "One capsule taken three times daily with food, or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "For adults only\n" +
                        "Not suitable for use during planned pregnancy, pregnancy, or breastfeeding\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_curcumin_plus"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_cranberryintensive",
                "Cranberry Intensive",
                "Cranberry Intensive is a unique formulation for women, containing a combination of cranberry, potassium salts, probiotics and hibiscus extract along with vitamin C.\n",
                "Herbal",
                "6",
                0,
                "Cranberry Extract (Vaccinium macrocarpon Berry), Potassium Citrate, Potassium Bicarbonate Bulking Agent (Microcrystalline Cellulose), Vitamin C (as Ascorbic Acid), Citric Acid, ellirose™ Hibiscus Extract (Hibiscus sabdariffa Flower), Magnesium Hydroxide, Lactobacillus acidophilus, Tricalcium Phosphate. ",
                "• Cranberry Intensive sachets can be mixed with water to provide a pleasant tasting cranberry drink\n" +
                        "• Cranberry extract naturally contains components such as d-mannose, benzoic and quinic acid\n" +
                        "• Vitamin C contributes to the normal function of the immune system\n" +
                        "• Vitamin C contributes to the protection of cells from oxidative stress\n" +
                        "• Contains alkaline potassium salts and Lactobacillus acidophilus\n",
                "One 10g sachet daily dissolved in water and taken with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Refrigerate below 4&deg;C and avoid direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_cranberryintensive"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_mitoguard",
                "MitoGuard® 60 Capsules",
                "New Improved Formula - Now suitable for vegetarians & vegans. A synergistic high potency nutrient complex with D-Ribose, Korean Ginseng, CoQ10, B Vitamins, Alpha Lipoic Acid and Antioxidants.\n",
                "Specific Nutrient Complexes",
                "60",
                0,
                "D-Ribose, Capsule Shell (HPMC), N-Acetyl L-Carnitine Hydrochloride, Niacin (as Nicotinamide), Bulking Agent (Cellulose), Thiamin (as Thiamin Hydrochloride), CoEnzyme Q10, N-Acetyl L-Cysteine, Alpha Lipoic Acid, Anti-Caking Agents (Magnesium Stearate & Silicon Dioxide), Riboflavin, Korean Ginseng 10:1 Extract (Panax ginseng Root). ",
                "• Our new improved formula is now suitable for vegetarians and vegans\n" +
                        "• Provides optimal amounts of vitamin B2 and B3 essential components of energy production that contribute to the reduction of tiredness and fatigue\n" +
                        "• Nutrients are provided in the most bioavailable forms, in a convenient capsule\n" +
                        "• Trusted: Designed by nutrition experts at BioCare - the choice of professionals for over 30 years\n",
                "One capsule taken twice daily with food, or as professionally directed.\n  ",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Not suitable for use during planned pregnancy, pregnancy or breastfeeding\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_mitoguard"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_nutrisorbliquidbiomulsiond",
                "Children's BioMulsion® D (Liquid vitamin D)20ml",
                "Children's BioMulsion® D provides a high potency vitamin D3 in a delicious, natural lemon flavoured liquid, emulsified for optimal absorption. It provides 300iu per daily intake.\n",
                "Vitamins",
                "20ml",
                0,
                "Purified Water, Fructose, Acacia Gum, Extra Virgin Olive Oil, Natural Lemon Oil, Citric Acid, Antioxidants (Natural Mixed Tocopherols & DL-Alpha Tocopherol), Sunflower Oil, Medium Chain Triglycerides, Preservative (Potassium Sorbate), Vitamin D3 (as Cholecalciferol). ",
                "• High potency liquid vitamin D3 (cholecalciferol) 300i.u. per daily intake\n" +
                        "• Designed especially for children\n" +
                        "• The BioMulsion® emulsification process increases absorption and bio-availability\n" +
                        "• Liquid fruit base for delicious, effective and flexible use\n" +
                        "• Ideal for those who have difficulty swallowing capsules and tablets and for individuals with digestive and absorption difficulties. It can be added to water, fruit juice or milk\n" +
                        "• Vitamin D is needed for normal growth and development of bone in children\n" +
                        "• The Department of Health recommends a daily supplement of 7-8.5mcg vitamin D should be given to young children from 6 months to 5 years of age\n",
                "Three drops taken daily in water, juice or milk with food or as professionally directed. Shake well before use. Suitable from 3 years.\n",
                "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle. Do not exceed the stated recommended daily intake.\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children.\n" +
                        "If you are under medical supervision, please consult a doctor before use.\n" +
                        "Refrigerate after opening and consume within 6 months.\n",
                ImageRetriever.createUrl("biocare_nutrisorbliquidbiomulsiond"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_babyabcdplusdrops",
                "Baby A, C, D Plus Drops  15ml",
                "Baby A, C, D Plus Drops contains key nutrients at levels suitable for babies from one month in an easy to use liquid form.\n",
                "Multi-Nutrients",
                "15ml",
                0,
                "Water, Fructose, Zinc Ascorbate, Vitamin C (as Calcium Ascorbate & Ascorbic Acid), Modified Tapioca Starch, Modified Corn Starch, Vitamin B6 (as Pyridoxine Hydrochloride), Niacin (as Nicotinamide), Pantothenic Acid (as Calcium Pantothenate), Sucrose, Riboflavin (as Riboflavin-5-Phosphate Sodium), Vitamin A (as Retinyl Palmitate), Sunflower Oil, Thiamine (as Thiamine Hydrochloride), Antioxidant (Natural Mixed Tocopherols), Vitamin D (as Ergocalciferol). ",
                "• A liquid multivitamin formula suitable for babies from 1 month and up to 1 year\n" +
                        "• Contains vitamins A, C & D with B vitamins and zinc\n" +
                        "• Easy to take multivitamin for babies - can be mixed with milk, water or dropped into the mouth\n" +
                        "• Suitable for vegetarians and vegans\n" +
                        "• The Department of Health recommends, all infants and young children aged 6 months to 5 years take a daily supplement containing 7 to 8.5mcg of vitamin D\n" +
                        "• This product should not be given to: \n" +
                        "Infants consuming 500ml or more of infant formula a day. \n" +
                        "Infants under 1 month unless under medical supervision\n" +
                        "\n" +
                        "\n",
                "Eight drops daily or as professionally directed. Mix in milk, water or drop directly into baby's mouth. Suitable from one month.\n" +
                        "Shake well before use.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n" +
                        "Refrigerate after opening and consume within 6 months\n",
                ImageRetriever.createUrl("biocare_babyabcdplusdrops"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_babyvitaminddrops",
                "Baby Vitamin D Drops 15ml",
                "Baby Vitamin D Drops provides vitamin D3 in a pleasant, pure and simple olive oil base. It provides 300iu of vitamin D per daily intake (3 drops).\n",
                "Vitamins",
                "15ml",
                0,
                "Extra Virgin Olive Oil, Medium Chain Triglycerides, Vitamin D3 (as Cholecalciferol), Antioxidant (DL-Alpha Tocopherol). ",
                "• Ideal for babies and suitable from one month onwards.\n" +
                        "• Pure and simple liquid base, free from additives and preservatives\n" +
                        "• Vitamin D is needed for normal growth and development of bone in children\n" +
                        "• The UK government recommends that all children from 6 months to 5 years take a daily supplement  containing 7 to 8.5µg of vitamin D\n" +
                        "• (This product should not be given to infants consuming 500ml or more of infant formula a day or infants under 1 month unless under medical supervision.)\n",
                "Three drops taken daily in water, juice or milk, with food, or as professionally directed.\n" +
                        "Shake well before use. Suitable from 1 month.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Not suitable for infants consuming 500ml or more of formula milk a day\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_babyvitaminddrops"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_childrens_mindlinx_multi",
                "Children's Mindlinx® Multinutrient 150g",
                "Childrens's Mindlinx® Multinutrient powder is a unique combination of vitamins and minerals for children, with a high concentration of B vitamins, magnesium and zinc, in a tasty, easy to take, powder form.\n",
                "Multi-Nutrients",
                "150g",
                0,
                "Fructooligosaccharides (Chicorium intybus Root), Fructose, Magnesium Citrate, Calcium Citrate, Vitamin C (as Ascorbic Acid), Choline Bitartrate, Vitamin E (as D-Alpha Tocopheryl Succinate), Modified Corn Starch, Vitamin B6 (as Pyridoxine Hydrochloride), Pantothenic Acid (as Calcium Pantothenate), Dicalcium Phosphate, Zinc Citrate, Microcrystalline Cellulose, Niacin (as Nicotinamide), Natural Vanilla Flavour, Thiamine (as Thamine Mononitrate), Riboflavin (as Riboflavin-5-Phosphate Sodium), Olive Oil, Apricot Powder (Prunus armeniaca Fruit), Potato Maltodextrin, Beta Carotene, Sucrose, Acacia Gum, Corn Starch, Chromium Picolinate, Sunflower Oil, Vitamin A (as Retinyl Acetate), Manganese Gluconate, Antioxidants (Ascorbic Acid, Ascorbyl Palmitate, DL-Alpha Tocopherol & Natural Mixed Tocopherols), Potassium Molybdate, Sodium Selenite, Folic Acid, Corn Oil, Potassium Iodide, Biotin, Vitamin B12 (as Cyanocobalamin), Vitamin D2 (as Ergocalciferol), Vitamin K (as Phylloquinone). ",
                "• Children's Mindlinx® Multinutrient is a pleasant tasting powder multinutrient for children\n" +
                        "• Suitable from 3 years of age and ideal for those who have difficulties swallowing capsules or tablets\n" +
                        "• Vitamin D is needed for normal growth and development of bone in children\n" +
                        "• Iodine contributes to the normal growth of children\n" +
                        "• Wheat, gluten and dairy free formulation developed with the intolerant individual in mind\n",
                "Take daily with food as indicated or as professionally directed.\n" +
                        "From 3 - 8  years - One level teaspoon (approximately 5g), 9 years onwards - Two level teaspoons (approximately 10g), Can be mixed into water, milk or juice to take alongside food, or sprinkled directly onto food.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "Long term intake of amounts greater than 10mg vitamin B6 daily may lead to mild tingling and numbness\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_childrens_mindlinx_multi"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_childrenscompletecomplex",
                "Children's Complete Complex (Multinutrient) 150g",
                "Children's Complete Complex is a comprehensive vitamin and mineral formula for children in a pleasant tasting, easy to use powder form.\n",
                "Multi-Nutrients",
                "150g",
                0,
                "Fructooligosaccharides (F.O.S), Fructose, Calcium Citrate, Magnesium Citrate, Vitamin E (D-Alpha Tocopheryl Succinate), Vitamin C (as Ascorbic Acid), Vitamin B6 (as Pyridoxine Hydrochloride), Banana Powder (Musa sp. Fruit), Natural Vanilla Flavour, Pantothenic Acid (as Calcium Pantothenate), Zinc Citrate, Microcrystalline Cellulose, Choline Bitartrate, Inositol, Niacin (as Nicotinamide), Modified Corn Starch, Dicalcium Phosphate, Thiamine (as Thiamine Hydrochloride), Ferrous Fumarate, Riboflavin, Modified Tapioca Starch, Olive Oil, Beta Carotene, PABA, Copper Citrate, Vitamin A (as Retinyl Palmitate), Chromium Picolinate, Sucrose, Sunflower Oil, Sodium Molybdate, Antioxidants (Ascorbic Acid, Ascorbyl Palmitate & Natural Mixed Tocopherols), Manganese Citrate, Sodium Selenite, Folic Acid, Biotin, Acacia Gum, Vitamin D2 (as Ergocalciferol), Potassium Iodide, Vitamin B12 (as Hydroxycobalamin), Vitamin K1 (as Phylloquinone). ",
                "• Children's Complete Complex is a pleasant tasting powder multinutrient for children\n" +
                        "• Suitable from 6 months of age and ideal for young children or those who have difficulty swallowing capsules or tablets\n" +
                        "• Easily mixes into milk, juice, yoghurt or pureed foods\n" +
                        "• Dairy-free and formulated with the intolerant individual in mind\n" +
                        "• Contains vitamin D which is needed for normal growth and development of bone in children\n" +
                        "• Iodine contributes to the normal growth of children\n" +
                        "\n",
                "From 6 months - 1 year - Half a teaspoon (approximately 2.5g), 1 - 5 years - One level  teaspoon (approximately 5g),\n" +
                        "5 years onwards - Two level teaspoons (approximately 10g).\n" +
                        "Can be mixed into water, milk or juice to take alongside food, or sprinkled directly onto food.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Long term intake of amounts greater than 10mg of vitamin B6 daily may lead to mild tingling and numbness\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_childrenscompletecomplex"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_babybioflora",
                "Baby BioFlora 33g",
                "Baby BioFlora is a specialised blend of probiotics suitable from birth and containing LAB4B, the latest of BioCare's industry-leading LAB4 probiotic and G.O.S., which encourages the growth of friendly bacteria in the intestine of the baby.\n",
                "Probiotics",
                "33g",
                0,
                "Galactooligosaccharides (Milk), Fructooligosaccharides, Lactobacillus salivarius, Bifidobacterium bifidum and Bifidobacterium lactis, Lactobacillus paracasei.  ",
                "• Specialised proprietary blend of bacteria including Bifidobacterium bifidum & lactis, Lactobacillus paracasei and Lactobacillus salivarius\n" +
                        "• Suitable for breast-fed and formula-fed babies\n" +
                        "• Guaranteed 10 billion live bacteria per daily intake\n" +
                        "• Formulated with prebiotics Galactooligosaccharides (G.O.S.) and Fructooligosaccharides (F.O.S.)\n" +
                        "• Vaccum packed for stability\n" +
                        "• Human strain and stomach acid resistant bacteria\n" +
                        "• Free from artificial colours, preservatives and flavours\n" +
                        "• For full allergen information, scroll over the label above\n",
                "One heaped scoopful (approximately 1g) daily mixed into a small amount of sterile water or suitable milk to form a paste. Give orally alongside baby's feed.\n" +
                        "During weaning, Baby BioFlora can be mixed into cool or warm soft food. Suitable from birth onwards.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Vacuum sealed for stability\n" +
                        "Refrigerate below 4&deg;C and avoid direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_babybioflora"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_antenatalbioflora",
                "AnteNatal BioFlora 30 Caps",
                "AnteNatal BioFlora is a specialist live bacteria for use by women during pregnancy. It contains LAB4B, the latest of BioCare's industry-leading LAB4 range of live bacteria.\n",
                "Probiotics",
                "33g",
                0,
                "Fructooligosaccharides (F.O.S.), Capsule Shell (HPMC), Lactobacillus salivarius, Bifidobacterium bifidum & Bifidobacterium lactis, Lactobacillus paracasei.  ",
                "• Contains LAB 4 B as used in the Swansea baby trial\n" +
                        "• Specialist proprietary blend of bacteria including Bifidobacterium bifidum & lactis, Lactobacillus paracasei and Lactobacillus salivarius\n" +
                        "• Designed to be used by women during pregnancy, especially during the last trimester\n" +
                        "• Guaranteed 10 billion live bacteria per daily intake\n" +
                        "• Formulated with fructooligosaccharides (F.O.S.)\n" +
                        "• Vaccum sealed for stability\n" +
                        "• Human strain and stomach acid resistant bacteria\n" +
                        "• Free from artificial colours, preservatives and flavours\n",
                "One capsule taken daily with food or as professionally directed.\n",
                "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Vacuum sealed for stability\n" +
                        "Refrigerate below 4&deg;C and avoid direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_antenatalbioflora"),
                CompanyConstants.BIOCARE
        ));
        displayItemsDao.insert(new DisplayItems(
                "biocare_veganomega3",
                "Vegan Omega - 3 (Algal DHA & EPA) 30 Capsules",
                "Vegan Omega - 3 (Algal DHA & EPA) is one of the first supplements in the UK to give vegetarians and vegans a direct supply of important EPA & DHA from natural algae.\n",
                "Fatty Acids",
                "30 & 60",
                0,
                "life'sOMEGA™ (Algal Oil, High Oleic Sunflower Oil, Antioxidants [Natural Mixed Tocopherols & Ascorbyl Palmitate], Rosemary Extract), Capsule Shell (Modified Corn Starch, Glycerol, Carrageenan [Seagel Cap], Sodium Carbonate). ",
                "• DHA & EPA contribute to normal function of the heart with a daily intake of 250mg DHA & EPA\n" +
                        "• DHA contributes to the maintenance of normal brain function and vision with a daily intake of 250mg DHA\n" +
                        "• Suitable for daily use and during pregnancy (no added vitamin A or D)\n" +
                        "• Maternal intake of DHA contributes to the normal brain development of the foetus and breastfed infants and contributes to the normal visual development of infants up to 12 months of age\n" +
                        "• Approved by the Vegetarian Society\n",
                "One capsule taken daily with food, or as professionally directed.\n",
                "Caution is advised with anticoagulant medication\n" +
                        "If you are under medical supervision, please consult a doctor before use\n" +
                        "This product should not be used as a substitute for a varied and balanced diet and healthy lifestyle\n" +
                        "Do not exceed the stated recommended daily intake\n" +
                        "Do not purchase if the seal is broken\n" +
                        "Keep out of reach of children\n" +
                        "Store below 25&deg;C in a dry place away from direct sunlight and heat\n",
                ImageRetriever.createUrl("biocare_veganomega3"),
                CompanyConstants.BIOCARE
        ));

    }
}

