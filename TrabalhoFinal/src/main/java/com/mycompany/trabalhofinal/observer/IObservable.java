package com.mycompany.trabalhofinal.observer;

import com.mycompany.trabalhofinal.model.Usuario;




public interface IObservable {
    
    public void addObserver(IObserver observer);

    public void removeObserver(IObserver observer);

    public void notifyObservers(Usuario usuario);
}

