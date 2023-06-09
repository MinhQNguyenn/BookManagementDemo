USE [PRJ301_SP2023_B5_Afternoon]
GO
/****** Object:  Table [dbo].[Author]    Script Date: 4/25/2023 3:52:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Author](
	[aid] [int] NOT NULL,
	[aname] [varchar](150) NOT NULL,
 CONSTRAINT [PK_Author] PRIMARY KEY CLUSTERED 
(
	[aid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Book]    Script Date: 4/25/2023 3:52:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Book](
	[bid] [int] NOT NULL,
	[btitle] [varchar](150) NOT NULL,
	[publisheddate] [date] NOT NULL,
 CONSTRAINT [PK_Book] PRIMARY KEY CLUSTERED 
(
	[bid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Book_Author]    Script Date: 4/25/2023 3:52:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Book_Author](
	[bid] [int] NOT NULL,
	[aid] [int] NOT NULL,
 CONSTRAINT [PK_Book_Author] PRIMARY KEY CLUSTERED 
(
	[bid] ASC,
	[aid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Author] ([aid], [aname]) VALUES (1, N'A')
INSERT [dbo].[Author] ([aid], [aname]) VALUES (2, N'B')
INSERT [dbo].[Author] ([aid], [aname]) VALUES (3, N'C')
INSERT [dbo].[Author] ([aid], [aname]) VALUES (4, N'D')
GO
INSERT [dbo].[Book] ([bid], [btitle], [publisheddate]) VALUES (1, N'Book 1', CAST(N'2000-01-01' AS Date))
INSERT [dbo].[Book] ([bid], [btitle], [publisheddate]) VALUES (2, N'Book 2', CAST(N'2000-04-11' AS Date))
INSERT [dbo].[Book] ([bid], [btitle], [publisheddate]) VALUES (3, N'Book 3XX', CAST(N'2023-04-12' AS Date))
GO
INSERT [dbo].[Book_Author] ([bid], [aid]) VALUES (1, 1)
INSERT [dbo].[Book_Author] ([bid], [aid]) VALUES (1, 3)
INSERT [dbo].[Book_Author] ([bid], [aid]) VALUES (2, 2)
INSERT [dbo].[Book_Author] ([bid], [aid]) VALUES (2, 3)
INSERT [dbo].[Book_Author] ([bid], [aid]) VALUES (3, 2)
INSERT [dbo].[Book_Author] ([bid], [aid]) VALUES (3, 4)
GO
ALTER TABLE [dbo].[Book_Author]  WITH CHECK ADD  CONSTRAINT [FK_Book_Author_Author] FOREIGN KEY([aid])
REFERENCES [dbo].[Author] ([aid])
GO
ALTER TABLE [dbo].[Book_Author] CHECK CONSTRAINT [FK_Book_Author_Author]
GO
ALTER TABLE [dbo].[Book_Author]  WITH CHECK ADD  CONSTRAINT [FK_Book_Author_Book] FOREIGN KEY([bid])
REFERENCES [dbo].[Book] ([bid])
GO
ALTER TABLE [dbo].[Book_Author] CHECK CONSTRAINT [FK_Book_Author_Book]
GO
