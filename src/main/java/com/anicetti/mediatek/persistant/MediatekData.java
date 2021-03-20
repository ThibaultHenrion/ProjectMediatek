package com.anicetti.mediatek.persistant;

import java.util.ArrayList;
import java.util.List;

import com.anicetti.mediatek.persistant.auth.User;
import com.anicetti.mediatek.persistant.documents.Cd;
import com.anicetti.mediatek.persistant.documents.DocumentPersistant;
import com.anicetti.mediatek.persistant.documents.Dvd;
import mediatek2021.*;

// classe mono-instance : l'unique instance est connue de la bibliotheque
// via une injection de dèpendance dans son bloc static

public class MediatekData implements PersistentMediatek {
// Jean-François Brette 01/01/2018
	static {
		// injection dynamique de la dépendance dans le package stable mediatek2021
		Mediatek.getInstance().setData(new MediatekData());
	}

	private MediatekData() {
	}

	// renvoie la liste de tous les documents de la bibliothèque
	@Override
	public List<Document> catalogue(int type) {
		if(type == 0) {
			return new ArrayList<>(Cd.getAll());
		} else if(type == 1) {
			return new ArrayList<>(Dvd.getAll());
		}
		return new ArrayList<>();
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		return User.getOne(login, password);
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		return null;
	}

	// ajoute un nouveau document - exception à définir
	@Override
	public void newDocument(int type, Object... args) throws NewDocException {
		switch (type){
			case 0:
				Cd cd = new Cd((String)args[0], (String)args[1],
						Cd.GenreCd.valueOf((String) args[2]));
				cd.insert();
				break;
			case 1:
				Dvd dvd = new Dvd((String)args[0], (String) args[1],
						Dvd.GenreDvd.valueOf((String) args[2]), (boolean) args[3]);
				dvd.insert();
				break;
			default:
				throw new NewDocException("Type invalide.");
		}
	}

	// supprime un document - exception à définir
	@Override
	public void suppressDoc(int numDoc) throws SuppressException {
		DocumentPersistant.delete(numDoc);
	}

}
