package models;

public enum Category {

	DEPORTES,

	GEOGRAFIA,

	HISTORIA, FINAL, ARTEYLITERATURA,

	CIENCIAYTECNOLOGIA {
		@Override
		public String toString() {
			return "Ciencias y Tecnología";
		}
	},
	ESPECTACULOSYENTRETENIMIENTO;

	public static Category parse(String categ) {
		categ = categ.toLowerCase();

		if (categ.contains("arte")) {
			return Category.ARTEYLITERATURA;
		}
		if(categ.contains("final")){
			return Category.FINAL;
		}
		if (categ.contains("ciencia")) {
			return Category.CIENCIAYTECNOLOGIA;
		}

		if (categ.contains("deportes")) {
			return Category.DEPORTES;
		}

		if (categ.contains("entretenimiento")) {
			return Category.ESPECTACULOSYENTRETENIMIENTO;
		}

		if (categ.contains("geograf")) {
			return Category.GEOGRAFIA;
		}

		if (categ.contains("historia")) {
			return Category.HISTORIA;
		}

		return null;
	}
}
