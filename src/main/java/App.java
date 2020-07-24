import java.util.List;

import jdbc.dao.ArticleDaoJdbc;
import jdbc.dao.FournisseurDaoJdbc;
import jdbc.entities.Article;
import jdbc.entities.Fournisseur;

public class App {

	public static void main(String[] args) {

		System.out.println("-----------------------------------Liste des fournisseurs-----------------------------------------------");
		FournisseurDaoJdbc fDao = new FournisseurDaoJdbc();

		List<Fournisseur> listFou = fDao.extraire();

		for (Fournisseur fournisseur : listFou) {

			System.out.println(fournisseur + "\n");

		}

		System.out.println("Il y a " + listFou.size() + " fournisseurs avant insertion\r");

		Fournisseur newFou = new Fournisseur(666, "La Maison de la Peinture");

		fDao.insert(newFou);

		listFou = fDao.extraire();

		System.out.println("Il y a " + listFou.size() + " fournisseurs après insertion\r");

		newFou.setNom("Zoumbalaki");

		fDao.update(newFou);

		fDao.delete(newFou);

		listFou = fDao.extraire();



		System.out.println("-----------------------------------Liste des articles-----------------------------------------------");
		ArticleDaoJdbc aDao = new ArticleDaoJdbc();

		List<Article> listArt = aDao.extraire();

		for (Article article : listArt) {

			System.out.println(article + "\n");

		}

		System.out.println("Il y a " + listArt.size() + " articles avant insertion\r");

		Article newArt = new Article(125, "A263", "Scie à métaux", 56.99, 1);

		aDao.insert(newArt);

		listArt = aDao.extraire();

		System.out.println("Il y a " + listArt.size() + " articles après insertion\r");

		newArt.setDesignation("Scie à bois");

		aDao.update(newArt);

		aDao.delete(newArt);

		listArt = aDao.extraire();

	}

}
