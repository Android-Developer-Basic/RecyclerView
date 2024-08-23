package otus.gpb.recyclerview.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactViewModel:ViewModel() {
    var contacts = MutableLiveData<List<Contact>>()
    fun load(){
        var items: MutableList<Contact> = emptyList<Contact>().toMutableList()

        for (ii in 0..9){
            items.add(Contact.getRandom())
        }

        contacts.value = items
    }
}