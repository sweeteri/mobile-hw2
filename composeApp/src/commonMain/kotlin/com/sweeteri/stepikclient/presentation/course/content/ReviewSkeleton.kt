package com.sweeteri.stepikclient.presentation.course.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.sweeteri.stepikclient.presentation.ui.theme.StepikTheme

@Composable
fun ReviewSkeleton() {
    val skeleton = StepikTheme.colors.textSecondary.copy(alpha = 0.2f)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(skeleton)
            )

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.fillMaxWidth()) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(12.dp)
                            .background(skeleton, RoundedCornerShape(4.dp))
                    )

                    Spacer(Modifier.width(8.dp))

                    Row {
                        repeat(5) {
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .padding(1.dp)
                                    .background(skeleton, RoundedCornerShape(2.dp))
                            )
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))


                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .height(14.dp)
                        .background(skeleton, RoundedCornerShape(4.dp))
                )

                Spacer(Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(14.dp)
                        .background(skeleton, RoundedCornerShape(4.dp))
                )

                Spacer(Modifier.height(4.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(14.dp)
                        .background(skeleton, RoundedCornerShape(4.dp))
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        Divider(color = skeleton)
    }
}
