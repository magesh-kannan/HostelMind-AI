import { useEffect, useState } from "react";
import {
  Container,
  Typography,
  Grid,
  Card,
  CardContent,
} from "@mui/material";

import { getDashboard } from "../../services/dashboardService";
import MeetingRoomIcon from "@mui/icons-material/MeetingRoom";
import GroupsIcon from "@mui/icons-material/Groups";
import HotelIcon from "@mui/icons-material/Hotel";
import EventAvailableIcon from "@mui/icons-material/EventAvailable";
import PieChartIcon from "@mui/icons-material/PieChart";

function DashboardPage() {

  const [dashboard, setDashboard] = useState(null);

  useEffect(() => {
    loadDashboard();
  }, []);

  const loadDashboard = async () => {

    try {

      const data = await getDashboard();

      setDashboard(data);

    } catch (error) {

      console.error(error);

    }

  };

  if (!dashboard) {

    return (
      <Container sx={{ mt: 5 }}>
        <Typography>Loading Dashboard...</Typography>
      </Container>
    );

  }

  const cards = [
  {
    title: "Total Rooms",
    value: dashboard.totalRooms,
    icon: <MeetingRoomIcon fontSize="large" color="primary" />,
  },
  {
    title: "Total Students",
    value: dashboard.totalStudents,
    icon: <GroupsIcon fontSize="large" color="success" />,
  },
  {
    title: "Occupied Beds",
    value: dashboard.occupiedBeds,
    icon: <HotelIcon fontSize="large" color="error" />,
  },
  {
    title: "Available Beds",
    value: dashboard.availableBeds,
    icon: <EventAvailableIcon fontSize="large" color="success" />,
  },
  {
    title: "Occupancy %",
    value: dashboard.occupancyPercentage + "%",
    icon: <PieChartIcon fontSize="large" color="warning" />,
  },
];

  return (

    <Container sx={{ mt: 5 }}>

      <Typography
        variant="h4"
        fontWeight="bold"
        gutterBottom
      >
        HostelMind AI Dashboard
      </Typography>

      <Grid container spacing={3} sx={{ mt: 1 }}>

        {cards.map((card) => (

          <Grid item xs={12} sm={6} md={4} key={card.title}>

            <Card elevation={5}>

              <CardContent>

                <div
  style={{
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
  }}
>
  <Typography color="text.secondary">
    {card.title}
  </Typography>

  {card.icon}
</div>

                <Typography
                  variant="h4"
                  fontWeight="bold"
                >
                  {card.value}
                </Typography>

              </CardContent>

            </Card>

          </Grid>

        ))}

      </Grid>

    </Container>

  );

}

export default DashboardPage;