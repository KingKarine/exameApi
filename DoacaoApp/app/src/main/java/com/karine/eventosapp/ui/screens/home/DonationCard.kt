package com.karine.eventosapp.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.karine.eventosapp.api.model.Donation


@Composable
fun DonationCard(
    donation: Donation,
    onClick: (Int) -> Unit,
    canDelete: Boolean,
    onDelete: () -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            //.height(120.dp)
            .clickable { onClick(donation.id) },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, color = Color(0xFFe4e4e4)),
        elevation = CardDefaults.elevatedCardElevation(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color(0xFF51555d)),
        onClick = { onClick(donation.id) }
        ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = donation.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                if(canDelete){
                    IconButton(
                        onClick = onDelete
                    ) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                }
            }
            Text(
                text = "Quanidade: ${donation.quantity}",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = "Validade: ${donation.expirationDate}",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = "Local: ${donation.address}",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = "Contato: ${donation.contato}",
                style = MaterialTheme.typography.bodyMedium,
            )

        }
    }
}