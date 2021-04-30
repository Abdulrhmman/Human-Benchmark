package com.abdelrahmman.humanbenchmark.util

class Constants {

    companion object {

        // all words (300)
        val VOCABULARY = arrayOf("abject", "aberration", "abjure", "abnegation", "abrogate", "abscond", "abstruse", "accede", "accost",
            "accretion", "acumen", "adamant", "admonish", "adumbrate", "adverse", "advocate", "affluent", "aggrandize", "alacrity", "alias",
            "ambivalent", "amenable", "amorphous", "anachronistic", "anathema", "annex", "antediluvian", "antiseptic", "apathetic", "antithesis",
            "apocryphal", "approbation", "arbitrary", "arboreal", "arcane", "archetypal", "arrogate", "ascetic", "aspersion", "assiduous",
            "atrophy", "bane", "bashful", "beguile", "bereft", "blandishment", "bilk", "bombastic", "cajole", "callous", "calumny", "camaraderie",
            "candor", "capitulate", "carouse", "carp", "caucus", "cavort", "circumlocution", "circumscribe", "circumvent", "clamor", "cleave", "cobbler",
            "cogent", "cognizant", "commensurate", "complement", "compunction", "concomitant", "conduit", "conflagration", "congruity", "connive",
            "consign", "constituent", "construe", "contusion", "contrite", "contentious", "contravene", "convivial", "corpulence", "covet", "cupidity",
            "dearth", "debacle", "debauch", "debunk", "defunct", "demagogue", "denigrate", "derivative", "despot", "diaphanous", "didactic", "dirge",
            "disaffected", "discomfit", "disparate", "dispel", "disrepute", "divisive", "dogmatic", "dour", "duplicity", "duress", "eclectic", "edict",
            "ebullient", "egregious", "elegy", "elicit", "embezzlement", "emend", "emollient", "empirical", "emulate", "enervate", "enfranchise", "engender",
            "ephemeral", "epistolary", "equanimity", "equivocal", "espouse", "evanescent", "evince", "exacerbate", "exhort", "execrable", "exigent",
            "expedient", "expiate", "expunge", "extraneous", "extol", "extant", "expurgate", "fallacious", "fatuous", "fetter", "flagrant", "foil",
            "forbearance", "fortuitous", "fractious", "garrulous", "gourmand", "grandiloquent", "gratuitous", "hapless", "hegemony", "heterogenous",
            "iconoclast", "idiosyncratic", "impecunious", "impetuous", "impinge", "impute", "inane", "inchoate", "incontrovertible", "incumbent", "inexorable",
            "inimical", "injunction", "inoculate", "insidious", "instigate", "insurgent", "interlocutor", "intimation", "inure", "invective", "intransigent",
            "inveterate", "irreverence", "knell", "laconic", "largesse", "legerdemain", "libertarian", "licentious", "linchpin", "litigant", "maelstrom",
            "maudlin", "maverick", "mawkish", "maxim", "mendacious", "modicum", "morass", "mores", "munificent", "multifarious", "nadir", "negligent",
            "neophyte", "noisome", "noxious", "obdurate", "obfuscate", "obstreperous", "officious", "onerous", "ostensible", "ostracism", "palliate",
            "panacea", "paradigm", "pariah", "partisan", "paucity", "pejorative", "pellucid", "penchant", "penurious", "pert", "pernicious", "pertinacious",
            "phlegmatic", "philanthropic", "pithy", "platitude", "plaudit", "plenitude", "plethora", "portent", "potentate", "preclude", "predilection",
            "preponderance", "presage", "probity", "proclivity", "profligate", "promulgate", "proscribe", "protean", "prurient", "puerile", "pugnacious",
            "pulchritude", "punctilious", "quaint", "quixotic", "quandary", "recalcitrant", "redoubtable", "relegate", "remiss", "reprieve", "reprobate",
            "rescind", "requisition", "rife", "sanctimonious", "sanguine", "scurrilous", "semaphore", "serendipity", "sobriety", "solicitous", "solipsism",
            "spurious", "staid", "stolid", "subjugate", "surfeit", "surreptitious", "swarthy", "tangential", "tome", "toady", "torpid", "travesty", "trenchant",
            "trite", "truculent", "turpitude", "ubiquitous", "umbrage", "upbraid", "utilitarian", "veracity", "vestige", "vicissitude", "vilify", "virtuoso",
            "vitriolic", "vituperate", "vociferous", "wanton", "winsome", "yoke", "zephyr", "wily", "tirade")

        // 15 words
        val VOCAB10 = arrayOf("reprobate", "predilection", "partisan", "wily", "amenable", "abjure", "intimation", "injunction", "anathema", "pernicious",
            "engender", "contrite", "adverse", "nadir", "zephyr")

        // 55 words
        val VOCAB30 = arrayOf("reprobate", "predilection", "partisan", "wily", "amenable", "abjure", "intimation", "injunction", "anathema", "pernicious",
            "engender", "contrite", "adverse", "nadir", "zephyr", "enfranchise", "consign", "knell", "arcane", "despot",
            "vestige", "derivative", "pertinacious", "conflagration", "sobriety", "annex", "intransigent", "toady", "iconoclast", "accost",
            "expedient", "congruity", "heterogenous", "neophyte", "veracity", "libertarian", "preponderance", "sanguine", "circumvent", "portent",
            "contravene", "debauch", "bane", "obstreperous", "officious", "abnegation", "interlocutor", "maverick", "presage", "ebullient",
            "cajole", "pellucid", "expurgate", "tirade", "exigent")

        // 120 words
        val VOCAB75 = arrayOf("reprobate", "predilection", "partisan", "wily", "amenable", "abjure", "intimation", "injunction", "anathema", "pernicious",
            "engender", "contrite", "adverse", "nadir", "zephyr", "enfranchise", "consign", "knell", "arcane", "despot",
            "vestige", "derivative", "pertinacious", "conflagration", "sobriety", "annex", "intransigent", "toady", "iconoclast", "accost",
            "expedient", "congruity", "heterogenous", "neophyte", "veracity", "libertarian", "preponderance", "sanguine", "circumvent", "portent",
            "contravene", "debauch", "bane", "obstreperous", "officious", "abnegation", "interlocutor", "maverick", "presage", "ebullient",
            "cajole", "pellucid", "expurgate", "tirade", "exigent", "turpitude", "adumbrate", "sanctimonious", "beguile", "prurient",
            "inchoate", "embezzlement", "virtuoso", "expiate", "fetter", "inure", "accede", "instigate", "serendipity", "plethora",
            "dearth", "travesty", "obdurate", "edict", "atrophy", "complement", "fallacious", "camaraderie", "impecunious", "inveterate",
            "abnegation", "bilk", "dour", "bereft", "bombastic", "connive", "candor", "noxious", "penurious", "capitulate",
            "tangential", "compunction", "discomfit", "evince", "impute", "empirical", "carp", "licentious", "duplicity", "blandishment",
            "dirge", "philanthropic", "enervate", "pejorative", "constituent", "impetuous", "clamor", "advocate", "consign", "quixotic",
            "fatuous", "exhort", "callous", "vitriolic", "demagogue", "winsome", "divisive", "epistolary", "phlegmatic", "inimical")

    }


}