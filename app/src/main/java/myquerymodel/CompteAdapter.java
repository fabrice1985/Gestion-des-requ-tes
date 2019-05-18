package myquerymodel;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myquery.R;

import java.util.List;

public class CompteAdapter extends RecyclerView.Adapter<CompteAdapter.CompteViewHolder> {

    private List<Comptes> dataList;

    public CompteAdapter(List<Comptes> dataList){

        this.dataList = dataList;
    }
    @Override
    public CompteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_comptes, parent, false);
        return new CompteViewHolder(view);
    }
    @Override
    public void onBindViewHolder(CompteViewHolder holder, int position) {
        holder.txtComptePseudo.setText(dataList.get(position).getPseudo());
        holder.txtCompteNom.setText(dataList.get(position).getNom());
        holder.txtComptePrenom.setText(dataList.get(position).getPrenom());
        holder.txtCompteFiliere.setText(dataList.get(position).getFiliere());
        holder.txtCompteNiveau.setText(dataList.get(position).getNiveau());
        holder.txtCompteDateInscription.setText(dataList.get(position).getDate_inscription());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CompteViewHolder extends RecyclerView.ViewHolder {

        TextView txtComptePseudo,txtCompteNom, txtComptePrenom, txtCompteFiliere,txtCompteNiveau,txtCompteDateInscription;

        CompteViewHolder(View itemView) {
            super(itemView);
            txtComptePseudo = (TextView) itemView.findViewById(R.id.txt_compte_pseudo);
            txtCompteNom = (TextView) itemView.findViewById(R.id.txt_compte_nom);
            txtComptePrenom = (TextView) itemView.findViewById(R.id.txt_compte_prenom);
            txtCompteFiliere = (TextView) itemView.findViewById(R.id.txt_compte_filiere);
            txtCompteNiveau = (TextView) itemView.findViewById(R.id.txt_compte_niveau);
            txtCompteDateInscription = (TextView) itemView.findViewById(R.id.txt_compte_date_inscription);
        }
    }

}
